package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Reservation;
import queries.IQuery;
import queries.MakeReservation;

public class ReserveRoomComponent extends AbstractQueryComponent<Reservation> {

	public ReserveRoomComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Start Date (yyyymmdd)", "Enter End Date (yyyymmdd): ", "Enter Room Number: ",
				"Enter Street: ", "Enter House Number: ", "Enter Postal Code: ", "Enter Customer Id: " };
	}

	@Override
	protected void displayData(Reservation t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Reservation> createQuery(JTextField[] textFields) {
		String StartDate = textFields[0].getText();
		String EndDate = textFields[1].getText();
		int RoomNumber = Integer.valueOf(textFields[2].getText());
		String Street = textFields[3].getText();
		String HouseNumber = textFields[4].getText();
		String PostalCode = textFields[5].getText();
		int CustomerID = Integer.valueOf(textFields[6].getText());

		return new MakeReservation(StartDate, EndDate, RoomNumber, Street, HouseNumber, PostalCode, CustomerID);
	}

	@Override
	public String getDescription() {
		return "Reserve Room";
	}

}
