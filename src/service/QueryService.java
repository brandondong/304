package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.DatabaseConnection;
import model.Customer;
import model.RoomWithPrice;

/**
 * Runs SQL queries on the database
 */
public class QueryService {

	private final Connection connection = DatabaseConnection.instance().getConnection();

	private final QueryBuilder builder = new QueryBuilder();

	public Map<String, Object> queryCustomerInfo(int id, String[] selected) throws SQLException {
		String query = builder.customerInfo(id, selected);
		Map<String, Object> props = new HashMap<>();

		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				for (String attribute : selected) {
					props.put(attribute, rs.getObject(attribute));
				}
			}
			return props;
		}
	}

	public List<RoomWithPrice> getMinOrMaxPricedRoom(boolean isMax, String street, String houseNo, String postalCode)
			throws SQLException {
		String query = builder.minOrMaxPricedRoom(isMax, street, houseNo, postalCode);
		List<RoomWithPrice> rooms = new ArrayList<>();

		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				rooms.add(new RoomWithPrice(rs.getInt("RoomNumber"), rs.getString("Price")));
			}
			return rooms;
		}
	}

	public List<Customer> getCustomersReservingAllRoomsInBranch(String street, String houseNo, String postalCode) {
		// TODO just testing json responses for now
		return Collections.singletonList(new Customer(1, "Bob", "credit", "12344445555"));
	}

}
