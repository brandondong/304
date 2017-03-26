package model;

public class Reservation {
	int ConfirmationID;
	String StartDate;
	String EndDate;
	int RoomNumber;
	String Street;
	String HouseNumber;
	String PostalCode;
	int CustomerID;
	
	public Reservation(	int ConfirmationID, String StartDate, String EndDate, int RoomNumber, String Street,
			String HouseNumber, String PostalCode, int CustomerID){
		this.ConfirmationID = ConfirmationID;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.RoomNumber = RoomNumber;
		this.Street = Street;
		this.HouseNumber = HouseNumber;
		this.PostalCode = PostalCode;
		this.CustomerID = CustomerID;
	}
	
	public int getConfirmationID(){
		return ConfirmationID;
	}
	
	public int getCustomerID() {
		return CustomerID;
	}
	
	public String getStartDate() {
		return StartDate;
	}
	
	public String getEndDate() {
		return EndDate;
	}
}
