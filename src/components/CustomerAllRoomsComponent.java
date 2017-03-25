package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import model.Customer;
import queries.CustomersReservingAllRoomsInBranch;
import queries.IQuery;
import ui.QueryControl;

public class CustomerAllRoomsComponent extends AbstractQueryComponent<List<Customer>> {

	public CustomerAllRoomsComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("Enter House Number: "), QueryControl.text("Enter Street: "),
				QueryControl.text("Enter Postal Code: ") };
	}

	@Override
	protected void displayData(List<Customer> t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<List<Customer>> createQuery(JFormattedTextField[] textFields) {
		String houseNo = textFields[0].getText();
		String street = textFields[1].getText();
		String postalCode = textFields[2].getText();
		return new CustomersReservingAllRoomsInBranch(street, houseNo, postalCode);
	}

	@Override
	public String getDescription() {
		return "Query Customer Who Reserved All Rooms In Branch";
	}

}
