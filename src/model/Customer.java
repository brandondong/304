package model;

public class Customer {

	private final int id;

	private final String name;

	private final String paymentMethod;

	private final String phoneNumber;

	public Customer(int id, String name, String paymentMethod, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.paymentMethod = paymentMethod;
		this.phoneNumber = phoneNumber;
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

}
