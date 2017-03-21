package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomersReservingAllRoomsInBranch extends AbstractQuery<List<Customer>> {

	private final String street;

	private final String houseNo;

	private final String postalCode;

	public CustomersReservingAllRoomsInBranch(String street, String houseNo, String postalCode, Connection con) {
		super(con);
		this.street = street;
		this.houseNo = houseNo;
		this.postalCode = postalCode;
	}

	@Override
	protected List<Customer> parseResult(ResultSet rs) throws SQLException {
		List<Customer> customers = new ArrayList<>();
		while (rs.next()) {
			customers.add(new Customer(rs.getInt("CustomerID"), rs.getString("Name")));
		}
		return customers;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format(
				"WITH Bad(RoomNumber, Street, HouseNumber, PostalCode, CustomerID) AS "
						+ "(SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Room, Customer "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s' "
						+ "MINUS SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Reservation) "
						+ "SELECT CustomerID, Name FROM Customer WHERE CustomerID NOT IN (SELECT CustomerID FROM Bad)",
				street, houseNo, postalCode);
	}
}
