package model;

public class Customer {

	private final int id;
	private final String name;
	private String paymentMethod = "";
	private String phoneNumber = "";

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPaymentMethod(String pm) {
		paymentMethod = pm;
	}
	public void setPhoneNumber(String pn) {
		phoneNumber = pn;
	}
}