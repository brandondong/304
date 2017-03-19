package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

		return executeQuery(query, (rs) -> {
			Map<String, Object> props = new HashMap<>();
			if (rs.next()) {
				for (String attribute : selected) {
					props.put(attribute, rs.getObject(attribute));
				}
			}
			return props;
		});
	}

	public List<RoomWithPrice> getMinOrMaxPricedRoom(boolean isMax, String street, String houseNo, String postalCode)
			throws SQLException {
		String query = builder.minOrMaxPricedRoom(isMax, street, houseNo, postalCode);

		return executeQuery(query, (rs) -> {
			List<RoomWithPrice> rooms = new ArrayList<>();
			while (rs.next()) {
				rooms.add(new RoomWithPrice(rs.getInt("RoomNumber"), rs.getString("Price")));
			}
			return rooms;
		});
	}

	public List<Customer> getCustomersReservingAllRoomsInBranch(String street, String houseNo, String postalCode)
			throws SQLException {
		checkBranchExists(street, houseNo, postalCode);
		String query = builder.customersReservingAllRoomsInBranch(street, houseNo, postalCode);

		return executeQuery(query, (rs) -> {
			List<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("CustomerID"), rs.getString("Name")));
			}
			return customers;
		});
	}

	private void checkBranchExists(String street, String houseNo, String postalCode) throws SQLException {
		String query = builder.checkBranchExists(street, houseNo, postalCode);
		boolean missing = executeQuery(query, (rs) -> {
			rs.next();
			return rs.getInt("Count") == 0;
		});
		if (missing) {
			throw new SQLException("Failed to find Branch");
		}
	}

	private <T> T executeQuery(String query, SQLFunction<ResultSet, T> parser) throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			return parser.apply(rs);
		}
	}

}
