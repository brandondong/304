package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import model.Reservation;
import queries.IQuery;
import queries.MakeReservation;
import ui.QueryControl;

public class ReserveRoomComponent extends AbstractQueryComponent<Reservation> {

	public ReserveRoomComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("Enter Start Date (yyyymmdd)"),
				QueryControl.text("Enter End Date (yyyymmdd): "), QueryControl.integer("Enter Room Number: "),
				QueryControl.text("Enter Street: "), QueryControl.text("Enter House Number: "),
				QueryControl.text("Enter Postal Code: "), QueryControl.integer("Enter Customer ID: ") };
	}

	@Override
	protected void displayData(Reservation t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Reservation> createQuery(JFormattedTextField[] textFields) {
		String StartDate = textFields[0].getText();
		String EndDate = textFields[1].getText();
		int RoomNumber = (int) textFields[2].getValue();
		String Street = textFields[3].getText();
		String HouseNumber = textFields[4].getText();
		String PostalCode = textFields[5].getText();
		int CustomerID = (int) textFields[6].getValue();

		return new MakeReservation(StartDate, EndDate, RoomNumber, Street, HouseNumber, PostalCode, CustomerID);
	}

	@Override
	public String getDescription() {
		return "Reserve Room";
	}

}
