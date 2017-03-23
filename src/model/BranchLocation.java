package model;

public class BranchLocation {

	private final String street;

	private final String houseNumber;

	private final String postalCode;

	private final int aggregatedPrice;

	private final AggregateOperation aggregationType;

	public BranchLocation(String street, String houseNumber, String postalCode, int aggregatedPrice,
			AggregateOperation aggregationType) {
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.aggregatedPrice = aggregatedPrice;
		this.aggregationType = aggregationType;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public int getAggregatedPrice() {
		return aggregatedPrice;
	}

	public AggregateOperation getAggregationType() {
		return aggregationType;
	}

}
