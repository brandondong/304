package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class GetAllCustomers extends AbstractQuery<List<Customer>>{

	@Override
	protected List<Customer> parseResult(ResultSet rs) throws SQLException {
		List<Customer> customers = new ArrayList<>();
		while(rs.next()) {
			Customer c = new Customer(rs.getInt("CustomerID"), rs.getString("Name"));
			c.setPaymentMethod(rs.getString("PaymentMethod"));
			c.setPhoneNumber(rs.getString("PhoneNumber"));
			customers.add(c);
		}
		return customers;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format("SELECT * FROM Customer");
	}

}
