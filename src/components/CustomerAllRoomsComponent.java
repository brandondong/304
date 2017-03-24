package components;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Customer;
import queries.CustomersReservingAllRoomsInBranch;

public class CustomerAllRoomsComponent extends AbstractQueryComponent<List<Customer>> {

	public CustomerAllRoomsComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter House Number: ", "Enter Street: ", "Enter Postal Code: " };
	}

	@Override
	protected void executeQuery(JTextField[] textFields) {
		String HouseNumber = textFields[0].getText();
		String Street = textFields[1].getText();
		String PostalCode = textFields[2].getText();
		
		CustomersReservingAllRoomsInBranch c = new CustomersReservingAllRoomsInBranch(Street, HouseNumber, PostalCode);
		try{
			List<Customer> r = c.execute(con);
			mainFrame.dispose();
			displayData(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected void displayData(List<Customer> t) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	protected IQuery<List<Customer>> createQuery(JTextField[] textFields) {
//		String houseNo = textFields[0].getText();
//		String street = textFields[1].getText();
//		String postalCode = textFields[2].getText();
//		return new CustomersReservingAllRoomsInBranch(street, houseNo, postalCode);
//	}

}
