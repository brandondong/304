package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Reservation;
import queries.IQuery;
import queries.ModifyReservation;

public class ModifyReservationComponent extends AbstractQueryComponent<Reservation> {

	public ModifyReservationComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter New Check-In Date (yyyymmdd): ", "Enter New Check-out Date (yyyymmdd): ",
				"Enter Confirmation Number: ", "Enter Customer ID: " };
	}

	@Override
	protected IQuery<Reservation> createQuery(JTextField[] textFields) {
		String CheckIn = textFields[0].getText();
		String Checkout = textFields[1].getText();
		int ConfirmationID = Integer.valueOf(textFields[2].getText());
		int custID = Integer.valueOf(textFields[3].getText());

		return new ModifyReservation(CheckIn, Checkout, ConfirmationID, custID);
	}

	@Override
	protected void displayData(Reservation t) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		return "Modify Reservation";
	}

}
