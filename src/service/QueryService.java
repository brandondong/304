package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import core.DatabaseConnection;
import model.Customer;
import model.Room;

public class QueryService {

	private final Connection connection = DatabaseConnection.instance().getConnection();

	public List<Customer> getCustomersReservingAllRoomsInBranch(String branchId) {
		// TODO just testing json responses for now
		return Collections.singletonList(new Customer(1, "Bob", "credit", "12344445555"));
	}

	public Room getMostExpensiveRoom() {
		// TODO
		return new Room(1);
	}

}
