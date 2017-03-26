package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Reservation;

public class MakeReservation implements IQuery<Reservation> {
	String checkIn;
	String checkOut;
	int roomNumber;
	String street;
	String houseNumber;
	String postalCode;
	int customerID;
	int confirmationID;
	
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
		return new Reservation(confirmationID, checkIn,checkOut, roomNumber, street, houseNumber, postalCode, customerID);
	}
	
	@Override
	public Reservation execute(Connection con) throws SQLException {
		boolean valid = new CheckIfReservationValid().execute(con);
		if (Integer.parseInt(checkOut) <= Integer.parseInt(checkIn))
			throw new SQLException("Error making reservation. Check out date must be after check in date");
		else if (!valid)
			throw new SQLException("Error making reservation. Room not avaliable on the given dates");
		else{
			try (Statement stmt = con.createStatement()) {
				confirmationID = new getMaxID().execute(con) + 1;
				ResultSet rs = stmt.executeQuery(getQueryDefinition());
				return parseResult(rs);
			}
		}
	}

	protected String getQueryDefinition() {
		return String.format(
				"INSERT INTO Reservation "
				+ "VALUES (%d, to_date(%s, 'yyyymmdd'), to_date(%s, 'yyyymmdd'), %d, '%s', '%s', '%s', %d)",
				confirmationID, checkIn, checkOut, roomNumber, street, houseNumber, postalCode, customerID);
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
					+ "WHERE StartDate <= to_date('%s', 'yyyymmdd') AND (to_date('%s', 'yyyymmdd') <= EndDate) "
					+ "AND RoomNumber = %d AND HouseNumber = '%s' AND Street = '%s' AND PostalCode = '%s'",
					checkOut, checkIn, roomNumber, houseNumber, street, postalCode);
		}
	}
	
	private class getMaxID extends AbstractQuery<Integer> {

		@Override
		protected Integer parseResult(ResultSet rs) throws SQLException {
			rs.next();
			return rs.getInt("Max");
		}

		@Override
		protected String getQueryDefinition() {
			return String.format("SELECT MAX(ConfirmationID) AS Max FROM Reservation");
		}
	}
}
