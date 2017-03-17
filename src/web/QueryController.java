package web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import model.Room;
import service.QueryService;

/**
 * Handles REST requests
 */
@RestController
class QueryController {

	private final QueryService service = new QueryService();

	/**
	 * Retrieves the selected attributes of the customer with the given id
	 */
	@RequestMapping("/customer")
	public Map<String, Object> queryCustomerInfo(@RequestParam(value = "CustomerID") int id,
			@RequestParam(value = "selected") String[] selected) throws SQLException {
		return service.queryCustomerInfo(id, selected);
	}

	@RequestMapping("/division")
	public List<Customer> getCustomersReservingAllRoomsInBranch(@RequestParam(value = "Street") String street,
			@RequestParam(value = "HouseNumber") String houseNo,
			@RequestParam(value = "PostalCode") String postalCode) {
		return service.getCustomersReservingAllRoomsInBranch(street, houseNo, postalCode);
	}

	@RequestMapping("/aggregate/most")
	public Room getMostExpensiveRoom() {
		return service.getMinOrMaxPricedRoom(true);
	}

	@RequestMapping("/aggregate/least")
	public Room getLeastExpensiveRoom() {
		return service.getMinOrMaxPricedRoom(false);
	}

}
