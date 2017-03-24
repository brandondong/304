package main;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.AggregateOperation;

public class PerformQuery {

	private final Connection con;

	private final JFrame mainFrame;

	public PerformQuery(Connection con, JFrame mainFrame) {
		this.con = con;
		this.mainFrame = mainFrame;
	}

	public void ReserveRoomExecute(JTextField[] RRTextFields) {
		String StartDate = RRTextFields[0].getText();
		String EndDate = RRTextFields[1].getText();
		int RoomNumber = Integer.valueOf(RRTextFields[2].getText());
		String Street = RRTextFields[3].getText();
		String HouseNumber = RRTextFields[4].getText();
		String PostalCode = RRTextFields[5].getText();
		int CustomerID = Integer.valueOf(RRTextFields[6].getText());
	}

	public void CheckInExecute(JTextField[] CTTextFields) {
		int ConfirmationID = Integer.valueOf(CTTextFields[0].getText());
		int CustomerID = Integer.valueOf(CTTextFields[1].getText());
	}

	public void ModifyReservationExecute(JTextField[] MRTextFields) {
		String CheckIn = MRTextFields[0].getText();
		String Checkout = MRTextFields[1].getText();
		int ConfirmationID = Integer.valueOf(MRTextFields[2].getText());
	}

	public void CheckOutExecute(JTextField[] COTextFields) {
		int ConfirmationID = Integer.valueOf(COTextFields[0].getText());
	}

	public void RoomAmenitiesExecute(JTextField[] RATextFields) {
		int RoomNumber = Integer.valueOf(RATextFields[0].getText());
		String Street = RATextFields[1].getText();
		String HouseNumber = RATextFields[2].getText();
		String PostalCode = RATextFields[3].getText();
	}

	public void RoomPriceExecute(JTextField[] RPTextFields) {
		int Price = Integer.valueOf(RPTextFields[0].getText());
		boolean Above = Integer.valueOf(RPTextFields[1].getText()) == 1;
		String Street = RPTextFields[2].getText();
	}

	public void LateCheckOutExecute(JTextField[] LCOTextFields) {
		String Date = LCOTextFields[0].getText();
		String ManagerID = LCOTextFields[1].getText();
	}

	public void CustInfoExecute(JTextField[] CITextFields) {
		int CustomerID = Integer.valueOf(CITextFields[0].getText());
		boolean Name = Integer.valueOf(CITextFields[1].getText()) == 1;
		boolean PhoneNumber = Integer.valueOf(CITextFields[2].getText()) == 1;
		boolean PaymentMethod = Integer.valueOf(CITextFields[3].getText()) == 1;
	}

	public void CustAllRoomExecute(JTextField[] CARTextFields) {
		String HouseNumber = CARTextFields[0].getText();
		String Street = CARTextFields[1].getText();
		String PostalCode = CARTextFields[2].getText();
	}

	public void MLExecute(JTextField[] MLTextFields) {
		String HouseNumber = MLTextFields[0].getText();
		String Street = MLTextFields[1].getText();
		String PostalCode = MLTextFields[2].getText();
		boolean Highest = Integer.valueOf(MLTextFields[3].getText()) == 1;
	}

	public void AggrPriceExecute(JTextField[] APTextFields) {
		String HouseNumber = APTextFields[0].getText();
		String Street = APTextFields[1].getText();
		String PostalCode = APTextFields[2].getText();
		AggregateOperation aggregate = AggregateOperation.valueOf(APTextFields[3].getText());
	}
}
