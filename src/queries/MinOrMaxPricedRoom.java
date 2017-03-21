package queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Room;

public class MinOrMaxPricedRoom extends AbstractQuery{
	
	public MinOrMaxPricedRoom(boolean isMax, String street, String houseNo, String postalCode, Connection con){
		super(con);
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		query = minOrMaxPricedRoomToString(isMax, street, houseNo, postalCode);
	}
	
	public List<Room> getResult(){
		List<Room> rooms = new ArrayList<>();
		try {
			while (rs.next()) {
				rooms.add(new Room(rs.getInt("RoomNumber"), rs.getInt("Price")));
			}
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		return rooms;
	}
	
	private String minOrMaxPricedRoomToString(boolean isMax, String street, String houseNo, String postalCode) {
		String maxOrMin = isMax ? "MAX" : "MIN";
		return String.format(
				"WITH FilteredRoom (RoomNumber, Price) AS "
						+ "(SELECT RoomNumber, Price FROM Room INNER JOIN RoomType on Room.TypeName = RoomType.TypeName "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s') "
						+ "SELECT * FROM FilteredRoom WHERE Price = (SELECT %s(Price) FROM FilteredRoom)",
				street, houseNo, postalCode, maxOrMin);
	}

}
