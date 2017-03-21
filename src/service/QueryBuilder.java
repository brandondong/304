package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
						+ "(SELECT RoomNumber, Price FROM Room INNER JOIN RoomType on Room.TypeName = RoomType.TypeName "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s') "
						+ "SELECT * FROM FilteredRoom WHERE Price = (SELECT %s(Price) FROM FilteredRoom)",
				street, houseNo, postalCode, maxOrMin);
	}

	public String customersReservingAllRoomsInBranch(String street, String houseNo, String postalCode) {
		return String.format(
				"WITH Bad(RoomNumber, Street, HouseNumber, PostalCode, CustomerID) AS "
						+ "(SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Room, Customer "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s' "
						+ "MINUS SELECT RoomNumber, Street, HouseNumber, PostalCode, CustomerID FROM Reservation) "
						+ "SELECT CustomerID, Name FROM Customer WHERE CustomerID NOT IN (SELECT CustomerID FROM Bad)",
				street, houseNo, postalCode);
	}

	public String checkIn(int confirmID, int cost) {
		return String.format("INSERT into RentCost values (%d, %d)", confirmID, cost);
	}

	public String checkBranchExists(String street, String houseNo, String postalCode) {
		return String.format(
				"SELECT COUNT(*) AS COUNT FROM Branch "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s'",
				street, houseNo, postalCode);
	}

	public String checkReservationExists(int confirmID, int custID) {
		String current = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		return String.format("SELECT COUNT(*) AS COUNT FROM Reservation WHERE ConfirmationID = %d AND CustomerID = %d "
				+ "AND to_date('%s', 'yyyymmdd') BETWEEN StartDate AND EndDate", confirmID, custID, current);
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
