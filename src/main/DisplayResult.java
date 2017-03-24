package main;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import model.BranchLocation;
import model.Customer;
import model.Reservation;
import model.Room;
import model.RoomWithAmenities;

public class DisplayResult extends PerformQuery {

	public DisplayResult(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	public void ReserveRoomDisplay(Reservation res) {
		// TODO Auto-generated method stub
		
	}

	public void CheckInDisplay() {
		// TODO Auto-generated method stub
		
	}

	public void ModifyReservationDisplay(Reservation r) {
		// TODO Auto-generated method stub
		
	}

	public void CheckOutDisplay(int i) {
		// TODO Auto-generated method stub
		
	}

	public void RoomAmenitiesDisplay(RoomWithAmenities r) {
		// TODO Auto-generated method stub
		
	}

	public void RoomPriceDisplay(List<Room> r) {
		// TODO Auto-generated method stub
		
	}

	public void LateCheckOutDisplay(List<Customer> r) {
		// TODO Auto-generated method stub
		
	}

	public void CustomerInfoDisplay(Map<String, Object> r) {
		// TODO Auto-generated method stub
		
	}

	public void CustAllRoomDisplay(List<Customer> r) {
		// TODO Auto-generated method stub
		
	}

	public void MLDisplay(List<Room> r) {
		// TODO Auto-generated method stub
		
	}

	public void AggrPriceDisplay(List<BranchLocation> r) {
		// TODO Auto-generated method stub
		
	}

}
