package web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/division")
	public List<Customer> getCustomersReservingAllRoomsInBranch(String branchId) {
		return service.getCustomersReservingAllRoomsInBranch(branchId);
	}

	@RequestMapping("/aggregate/most")
	public Room getMostExpensiveRoom() {
		return service.getMostExpensiveRoom();
	}

}
