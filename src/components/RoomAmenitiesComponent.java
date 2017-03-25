package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.IQuery;

public class RoomAmenitiesComponent extends AbstractQueryComponent<Object> {

	public RoomAmenitiesComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Room Number:  ", "Enter Street: ", "Enter House Number: ", "Enter Postal Code: " };
	}

	@Override
	protected IQuery<Object> createQuery(JTextField[] textFields) {
		int RoomNumber = Integer.valueOf(textFields[0].getText());
		String Street = textFields[1].getText();
		String HouseNumber = textFields[2].getText();
		String PostalCode = textFields[3].getText();
		// TODO implement query
		return null;
	}

	@Override
	protected void displayData(Object t) {
		// TODO Auto-generated method stub

	}

}
