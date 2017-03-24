package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.AggregateOperation;
import model.BranchLocation;
import model.Customer;
import model.Reservation;
import model.Room;
import model.RoomWithAmenities;
import queries.AggregatePriceByBranch;
import queries.CheckIn;
import queries.CheckOut;
import queries.CustomerInfo;
import queries.CustomersReservingAllRoomsInBranch;
import queries.LateCheckOut;
import queries.MakeReservation;
import queries.MinOrMaxPricedRoom;
import queries.ModifyReservation;
import queries.RoomAboveOrBelowPrice;
import queries.RoomAmenities;

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
		
		MakeReservation r = new MakeReservation(StartDate, EndDate, RoomNumber, Street, HouseNumber, PostalCode, CustomerID);
		try {
			Reservation res = r.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).ReserveRoomDisplay(res);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).ReserveRoom();
		}
	
	}

	public void CheckInExecute(JTextField[] CTTextFields) {
		int ConfirmationID = Integer.valueOf(CTTextFields[0].getText());
		int CustomerID = Integer.valueOf(CTTextFields[1].getText());
		int Cost = Integer.valueOf(CTTextFields[2].getText());
		
		CheckIn c = new CheckIn(ConfirmationID, CustomerID, Cost);
		try {
			c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).CheckInDisplay();
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).CheckIn();
		}
	}

	public void ModifyReservationExecute(JTextField[] MRTextFields) {
		String CheckIn = MRTextFields[0].getText();
		String Checkout = MRTextFields[1].getText();
		int ConfirmationID = Integer.valueOf(MRTextFields[2].getText());
		
		ModifyReservation mr = new ModifyReservation(CheckIn, Checkout, ConfirmationID);
		try{
			Reservation r = mr.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).ModifyReservationDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).ModifyReservation();
		}
	}

	public void CheckOutExecute(JTextField[] COTextFields) {
		int ConfirmationID = Integer.valueOf(COTextFields[0].getText());
		
		CheckOut c = new CheckOut(ConfirmationID);
		
		try{
			int i = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).CheckOutDisplay(i);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).CheckOut();
		}
	}

	public void RoomAmenitiesExecute(JTextField[] RATextFields) {
		int RoomNumber = Integer.valueOf(RATextFields[0].getText());
		String Street = RATextFields[1].getText();
		String HouseNumber = RATextFields[2].getText();
		String PostalCode = RATextFields[3].getText();
		
		RoomAmenities c = new RoomAmenities(RoomNumber, Street, HouseNumber, PostalCode);
		
		try{
			RoomWithAmenities r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).RoomAmenitiesDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).RoomAmenities();
		}
	}

	public void RoomPriceExecute(JTextField[] RPTextFields) {
		int Price = Integer.valueOf(RPTextFields[0].getText());
		boolean Above = Integer.valueOf(RPTextFields[1].getText()) == 1;
		String Street = RPTextFields[2].getText();
		String HouseNumber = RPTextFields[3].getText();
		String PostalCode = RPTextFields[4].getText();
		
		RoomAboveOrBelowPrice c = new RoomAboveOrBelowPrice(Price, Above, Street, HouseNumber, PostalCode);
		try{
			List<Room> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).RoomPriceDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).RoomPrice();
		}
	}

	public void LateCheckOutExecute(JTextField[] LCOTextFields) {
		String Date = LCOTextFields[0].getText();
		String ManagerID = LCOTextFields[1].getText();
		
		LateCheckOut c = new LateCheckOut(Date,ManagerID);
		try{
			List<Customer> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).LateCheckOutDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).LateCheckOut();
		}
	}

	public void CustInfoExecute(JTextField[] CITextFields) {
		int CustomerID = Integer.valueOf(CITextFields[0].getText());
		boolean Name = Integer.valueOf(CITextFields[1].getText()) == 1;
		boolean PhoneNumber = Integer.valueOf(CITextFields[2].getText()) == 1;
		boolean PaymentMethod = Integer.valueOf(CITextFields[3].getText()) == 1;
		
		int count = ((Name) ? 1 : 0)+((PhoneNumber) ? 1 : 0) + ((PaymentMethod) ? 1 : 0);
		String[] args = new String[count];
		int i = 0;
		if (Name){
			args[i] = "Name";
			i++;
		}
		if (PhoneNumber){
			args[i] = "PhoneNumber";
			i++;
		}
		if (PaymentMethod){
			args[i] = "PaymentMethod";
		}

		CustomerInfo c = new CustomerInfo(CustomerID, args);
		try{
			Map<String,Object> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).CustomerInfoDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).CustInfo();
		}
	}

	public void CustAllRoomExecute(JTextField[] CARTextFields) {
		String HouseNumber = CARTextFields[0].getText();
		String Street = CARTextFields[1].getText();
		String PostalCode = CARTextFields[2].getText();
		
		CustomersReservingAllRoomsInBranch c = new CustomersReservingAllRoomsInBranch(Street, HouseNumber, PostalCode);
		try{
			List<Customer> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).CustAllRoomDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).CustAllRoom();
		}
		
	}

	public void MLExecute(JTextField[] MLTextFields) {
		String HouseNumber = MLTextFields[0].getText();
		String Street = MLTextFields[1].getText();
		String PostalCode = MLTextFields[2].getText();
		boolean Highest = Integer.valueOf(MLTextFields[3].getText()) == 1;
		
		MinOrMaxPricedRoom c = new MinOrMaxPricedRoom(Highest, Street, HouseNumber, PostalCode);
		try{
			List<Room> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).MLDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).ML();
		}
	}

	public void AggrPriceExecute(JTextField[] APTextFields) {
		AggregateOperation aggregate = AggregateOperation.valueOf(APTextFields[3].getText());
		
		AggregatePriceByBranch c = new AggregatePriceByBranch(aggregate);
		try{
			List<BranchLocation> r = c.execute(con);
			mainFrame.dispose();
			new DisplayResult(con, mainFrame).AggrPriceDisplay(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			new CollectInput(con, mainFrame).AggrPrice();
		}
	}
}
