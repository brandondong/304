package components;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public String getDescription() {
		return "Modify Reservation";
	}

	@Override
	protected List<List<String>> parseData(Reservation t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("ConfirmationID", "StartDate", "EndDate", "RoomNumber", "Street",
				"HouseNumber", "PostalCode", "CustomerID"));
		data.add(Arrays.asList(Integer.toString(t.getConfirmationID()), t.getStartDate(), t.getEndDate(),
				Integer.toString(t.getRoomNumber()), t.getStreet(), t.getPostalCode(), Integer.toString(t.getCustomerID())));
		return data;
	}

}
