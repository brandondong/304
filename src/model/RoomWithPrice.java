package model;

public class RoomWithPrice {

	private final int roomNumber;

	private final String price;

	public RoomWithPrice(int roomNumber, String price) {
		this.roomNumber = roomNumber;
		this.price = price;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getPrice() {
		return price;
	}

}
