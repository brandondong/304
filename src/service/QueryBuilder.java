package service;

/**
 * Creates SQL queries
 */
class QueryBuilder {

	public String customerInfo(int id, String[] selected) {
		return String.format("SELECT %s FROM Customer WHERE CustomerID = %d", formatAttributes(selected), id);
	}

	public String minOrMaxPricedRoom(boolean isMax, String street, String houseNo, String postalCode) {
		String maxOrMin = isMax ? "MAX" : "MIN";
		return String.format(
				"WITH FilteredRoom (RoomNumber, Price) AS "
						+ "(SELECT RoomNumber, Price FROM Room INNER JOIN RoomType on Room.TypeName = RoomType.TypeName WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s')"
						+ "SELECT * FROM FilteredRoom WHERE Price = (SELECT %s(Price) FROM FilteredRoom)",
				street, houseNo, postalCode, maxOrMin);
	}

	private String formatAttributes(String[] attributes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < attributes.length; i++) {
			s.append(attributes[i]);
			if (i != attributes.length - 1) {
				s.append(", ");
			}
		}
		return s.toString();
	}

}
