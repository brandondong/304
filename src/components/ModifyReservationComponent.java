package components;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Reservation;
import queries.ModifyReservation;

public class ModifyReservationComponent extends AbstractQueryComponent<Reservation> {

	public ModifyReservationComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter New Check-In Date (yyyymmdd): ", "Enter New Check-out Date (yyyymmdd): ",
				"Enter Confirmation Number: " };
	}

//	@Override
//	protected IQuery<Void> createQuery(JTextField[] textFields) {
//		String CheckIn = textFields[0].getText();
//		String Checkout = textFields[1].getText();
//		int ConfirmationID = Integer.valueOf(textFields[2].getText());
//		
//		return new ModifyReservation(CheckIn, Checkout, ConfirmationID);
//	}

	@Override
	protected void executeQuery(JTextField[] textFields) {
		String CheckIn = textFields[0].getText();
		String Checkout = textFields[1].getText();
		int ConfirmationID = Integer.valueOf(textFields[2].getText());
		
		ModifyReservation mr = new ModifyReservation(CheckIn, Checkout, ConfirmationID);
		try{
			Reservation r = mr.execute(con);
			mainFrame.dispose();
			displayData(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected void displayData(Reservation t) {
		// TODO Auto-generated method stub
		
	}

}
