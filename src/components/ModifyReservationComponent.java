package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.IQuery;

public class ModifyReservationComponent extends AbstractQueryComponent<Void> {

	public ModifyReservationComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter New Check-In Date (yyyymmdd): ", "Enter New Check-out Date (yyyymmdd): ",
				"Enter Confirmation Number: " };
	}

	@Override
	protected IQuery<Void> createQuery(JTextField[] textFields) {
		String CheckIn = textFields[0].getText();
		String Checkout = textFields[1].getText();
		int ConfirmationID = Integer.valueOf(textFields[2].getText());
		// TODO query hasn't been implemented yet
		return null;
	}

}
