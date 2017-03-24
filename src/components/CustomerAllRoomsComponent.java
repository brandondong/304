package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Customer;
import queries.CustomersReservingAllRoomsInBranch;
import queries.IQuery;

public class CustomerAllRoomsComponent extends AbstractQueryComponent<List<Customer>> {

	public CustomerAllRoomsComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter House Number: ", "Enter Street: ", "Enter Postal Code: " };
	}

	@Override
	protected IQuery<List<Customer>> createQuery(JTextField[] textFields) {
		String houseNo = textFields[0].getText();
		String street = textFields[1].getText();
		String postalCode = textFields[2].getText();
		return new CustomersReservingAllRoomsInBranch(street, houseNo, postalCode);
	}

}
