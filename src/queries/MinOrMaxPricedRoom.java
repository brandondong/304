package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Room;

public class MinOrMaxPricedRoom implements IQuery<List<Room>> {

	private final boolean isMax;

	private final String street;

	private final String houseNo;

	private final String postalCode;

	public MinOrMaxPricedRoom(boolean isMax, String street, String houseNo, String postalCode) {
		this.isMax = isMax;
		this.street = street;
		this.houseNo = houseNo;
		this.postalCode = postalCode;
	}

	protected List<Room> parseResult(ResultSet rs) throws SQLException {
		List<Room> rooms = new ArrayList<>();
		while (rs.next()) {
			rooms.add(new Room(rs.getInt("RoomNumber"), rs.getInt("Price")));
		}
		return rooms;
	}

	protected String getQueryDefinition() {
		String maxOrMin = isMax ? "MAX" : "MIN";
		return String.format(
				"WITH FilteredRoom (RoomNumber, Price) AS "
						+ "(SELECT RoomNumber, Price FROM Room INNER JOIN RoomType on Room.TypeName = RoomType.TypeName "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s') "
						+ "SELECT * FROM FilteredRoom WHERE Price = (SELECT %s(Price) FROM FilteredRoom)",
				street, houseNo, postalCode, maxOrMin);
	}

	@Override
	public List<Room> execute(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
