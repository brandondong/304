package model;

public class BranchLocation {

	private final String postalCode;

	private final int aggregatedPrice;
	
	private final String aggregationType;

	public BranchLocation(String postalCode, int aggregatedPrice, String aggregationType) {
		this.postalCode = postalCode;
		this.aggregatedPrice = aggregatedPrice;
		this.aggregationType = aggregationType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public int getAggregatedPrice() {
		return aggregatedPrice;
	}
	
	public String getAggregationType(){
		return aggregationType;
	}
}
