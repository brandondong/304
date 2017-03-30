package components;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Customer;
import model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import queries.DeleteCustomer;
import queries.IQuery;
import queries.GetAllCustomers;
import ui.QueryControl;

public class DeleteCustomerComponent extends AbstractQueryComponent<List<Customer>> {

	public DeleteCustomerComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Customer ID:  ") };
	}

	@Override
	protected IQuery<List<Customer>> createQuery(JFormattedTextField[] textFields) {
		int customerID = (int) textFields[0].getValue();
		try {
			new DeleteCustomer(customerID).execute(con);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mainFrame,
					String.format("An error occurred during query execution:\n%s", e.getMessage()), "Query Error",
					JOptionPane.ERROR_MESSAGE);
			mainFrame.dispose();
			render();
		}
		return new GetAllCustomers();
	}

	@Override
	public String getDescription() {
		return "Delete Customer";
	}

	@Override
	protected List<List<String>> parseData(List<Customer> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("CustomerID", "Name", "PaymentMethod", "PhoneNumber"));
		for (int i = 0; i < t.size(); i++){
			Customer c = t.get(i);
			data.add(Arrays.asList(Integer.toString(c.getId()), c.getName(), c.getPaymentMethod(), c.getPhoneNumber()));
		}
		return data;
	}

}
