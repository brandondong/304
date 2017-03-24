package model;

public class RoomWithAmenities {

	private final String roomType;
	private final int roomPrice;
	private final float InternetAccess;
	private final float Kitchen;
	private final float SatelliteTV;
	
	public RoomWithAmenities(String roomType, int roomPrice, float InternetAccess, float Kitchen, float SatelliteTV) {
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.InternetAccess = InternetAccess;
		this.Kitchen = Kitchen;
		this.SatelliteTV = SatelliteTV;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getRoomPrice() {
		return roomPrice;
	}
	
	public float getInternetAccess(){
		return InternetAccess;
	}
	
	public float getKitched(){
		return Kitchen;
	}
	
	public float getSatelliteTV(){
		return SatelliteTV;
	}
}
