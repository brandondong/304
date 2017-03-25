package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import model.Reservation;
import queries.IQuery;
import queries.ModifyReservation;
import ui.QueryControl;

public class ModifyReservationComponent extends AbstractQueryComponent<Reservation> {

	public ModifyReservationComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.date("Enter New Check-In Date (yyyy-mm-dd): "),
				QueryControl.date("Enter New Check-out Date (yyyy-mm-dd): "),
				QueryControl.integer("Enter Confirmation Number: "), QueryControl.integer("Enter Customer ID: ") };
	}

	@Override
	protected IQuery<Reservation> createQuery(JFormattedTextField[] textFields) {
		String CheckIn = textFields[0].getText().replace("-", "");
		String Checkout = textFields[1].getText().replace("-", "");
		int ConfirmationID = (int) textFields[2].getValue();
		int custID = (int) textFields[3].getValue();

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
