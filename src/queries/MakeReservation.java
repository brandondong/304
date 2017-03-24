package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Reservation;

//**** Still being worked on. Currently does not work
public class MakeReservation implements IQuery<Reservation> {
	String checkIn;
	String checkOut;
	int roomNumber;
	String street;
	String houseNumber;
	String postalCode;
	int customerID;
	
	public MakeReservation(String checkIn, String checkOut, int roomNumber, String street, String houseNumber, String postalCode, int customerID) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.roomNumber = roomNumber;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.customerID = customerID;
	}

	protected Reservation parseResult(ResultSet rs) throws SQLException {
		rs.next();
		return new Reservation(customerID, checkIn,checkOut, roomNumber, street, houseNumber, postalCode, customerID);
	}
	
	@Override
	public Reservation execute(Connection con) throws SQLException {
		boolean valid = new CheckIfReservationValid().execute(con);
		if (valid){
			try (Statement stmt = con.createStatement()) {
				System.out.println("1");
				ResultSet rs = stmt.executeQuery(getQueryDefinition());
				System.out.println("here");
				customerID = new GetConfirmationID().execute(con);
				System.out.println("2");
				return parseResult(rs);
			}
		}
		else
			return null;
	}

	protected String getQueryDefinition() {
		return String.format(
				"INSERT INTO Reservation "
				+ "VALUES (reservation_id.nextval, to_date(%s, 'yyyymmdd'), to_date(%s, 'yyyymmdd'), %d, '%s', '%s', '%s', %d)",
				checkIn, checkOut, roomNumber, street, houseNumber, postalCode, customerID);
	}
	
	private class CheckIfReservationValid extends AbstractQuery<Boolean> {

		@Override
		protected Boolean parseResult(ResultSet rs) throws SQLException {
			rs.next();
			if ((rs.getInt("Count") != 0) || (checkIn.compareTo(checkOut)) >= 0)
				return false;
			else 
				return true;	
		}

		@Override
		protected String getQueryDefinition() {
			return String.format(
					"SELECT COUNT(*) AS Count FROM Reservation "
					+ "WHERE ((to_date(%s, 'yyyymmdd') BETWEEN StartDate AND EndDate) "
					+ "OR (to_date(%s, 'yyyymmdd') BETWEEN StartDate AND EndDate)) "
					+ "AND RoomNumber = %d AND HouseNumber = '%s' AND Street = '%s' AND PostalCode = '%s'",
					checkIn, checkOut, roomNumber, houseNumber, street, postalCode);
		}
	}
	
	private class GetConfirmationID extends AbstractQuery<Integer>{
		
		@Override
		protected Integer parseResult(ResultSet rs) throws SQLException {
			rs.next();
			return rs.getInt("ConfirmationID");
		}

		@Override
		protected String getQueryDefinition() {
			return "SELECT last_insert_rowid()";
		}
	}
}
