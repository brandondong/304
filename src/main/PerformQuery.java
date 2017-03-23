package main;

import java.sql.Connection;

import model.AggregateOperation;

public class PerformQuery extends CollectInput{

	public PerformQuery(Connection con) {
		super(con);
	}

	public void ReserveRoomExecute() {
		String StartDate = RRTextFields[0].getText();
		String EndDate = RRTextFields[1].getText();
		int RoomNumber = Integer.valueOf(RRTextFields[2].getText());
		String Street = RRTextFields[3].getText();
		String HouseNumber = RRTextFields[4].getText();
		String PostalCode = RRTextFields[5].getText();
		int CustomerID = Integer.valueOf(RRTextFields[6].getText());
	}

	public void CheckInExecute() {
		int ConfirmationID = Integer.valueOf(CTTextFields[0].getText());
		int CustomerID = Integer.valueOf(CTTextFields[1].getText());
	}

	public void ModifyReservationExecute() {
		String CheckIn = MRTextFields[0].getText();
		String Checkout = MRTextFields[1].getText();
		int ConfirmationID = Integer.valueOf(MRTextFields[2].getText());
	}

	public void CheckOutExecute() {
		int ConfirmationID = Integer.valueOf(COTextFields[0].getText());		
	}

	public void RoomAmenitiesExecute() {
		int RoomNumber = Integer.valueOf(RATextFields[0].getText());
		String Street = RATextFields[1].getText();
		String HouseNumber = RATextFields[2].getText();
		String PostalCode = RATextFields[3].getText();		
	}

	public void RoomPriceExecute() {
		int Price = Integer.valueOf(RPTextFields[0].getText());
		boolean Above = (Integer.valueOf(RPTextFields[1].getText()) == 1)? true: false;
		String Street = RPTextFields[2].getText();
	}

	public void LateCheckOutExecute() {
		String Date = LCOTextFields[0].getText();
		String ManagerID = LCOTextFields[1].getText();
	}

	public void CustInfoExecute() {
		int CustomerID = Integer.valueOf(CITextFields[0].getText());
		boolean Name = (Integer.valueOf(CITextFields[1].getText()) == 1)?true:false;
		boolean PhoneNumber = (Integer.valueOf(CITextFields[2].getText()) == 1)?true:false;
		boolean PaymentMethod = (Integer.valueOf(CITextFields[3].getText()) == 1)?true:false;
	}

	public void CustAllRoomExecute() {
		String HouseNumber = CARTextFields[0].getText();
		String Street = CARTextFields[1].getText();
		String PostalCode = CARTextFields[2].getText();		
	}

	public void MLExecute() {
		String HouseNumber = MLTextFields[0].getText();
		String Street = MLTextFields[1].getText();
		String PostalCode = MLTextFields[2].getText();	
		boolean Highest = (Integer.valueOf(MLTextFields[3].getText()) == 1)?true:false;
	}

	public void AggrPriceExecute() {
		String HouseNumber = APTextFields[0].getText();
		String Street = APTextFields[1].getText();
		String PostalCode = APTextFields[2].getText();	
		AggregateOperation aggregate = (APTextFields[3].getText()).equals("MAX")? AggregateOperation.MAX : 
			(APTextFields[3].getText()).equals("MIN")? AggregateOperation.MIN : 
			(APTextFields[3].getText()).equals("AVG")? AggregateOperation.AVG : AggregateOperation.COUNT;
	}
}
