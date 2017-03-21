package queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomersReservingAllRoomsInBranch extends AbstractQuery {

	public CustomersReservingAllRoomsInBranch(String street, String houseNo, String postalCode, Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		query = customersReservingAllRoomsInBranchToString(street, houseNo, postalCode);
	}

	public List<Customer> getResult() {
		List<Customer> customers = new ArrayList<>();
		try {
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("CustomerID"), rs.getString("Name")));
			}
		} catch (SQLException e) {
			 System.out.println("Message: " + e.getMessage());
			 System.exit(-1);
		}
		return customers;
	}

	private String customersReservingAllRoomsInBranchToString(String street, String houseNo, String postalCode) {
		return String.format(
				"WITH Bad(RoomNumber, Street, HouseNumber, PostalCode, CustomerID) AS "
						+ "(SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Room, Customer "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s' "
						+ "MINUS SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Reservation) "
						+ "SELECT CustomerID, Name FROM Customer WHERE CustomerID NOT IN (SELECT CustomerID FROM Bad)",
				street, houseNo, postalCode);
	}
}
