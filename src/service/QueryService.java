package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.DatabaseConnection;
import model.Customer;
import model.Room;

public class QueryService {

	private final Connection connection = DatabaseConnection.instance().getConnection();

	public Map<String, Object> queryCustomerInfo(int id, String[] selected) throws SQLException {
		String query = String.format("SELECT %s FROM Customer WHERE CustomerID = %d", formatAttributes(selected), id);
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

	public List<Customer> getCustomersReservingAllRoomsInBranch(String street, String houseNo, String postalCode) {
		// TODO just testing json responses for now
		return Collections.singletonList(new Customer(1, "Bob", "credit", "12344445555"));
	}

	public Room getMinOrMaxPricedRoom(boolean isMax) {
		// TODO
		return new Room(1);
	}

	private String formatAttributes(String[] attributes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < attributes.length; i++) {
			s.append(attributes[i]);
			if (i != attributes.length - 1) {
				s.append(", ");
			}
		}
		return s.toString();
	}

}
