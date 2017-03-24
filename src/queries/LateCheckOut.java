package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Customer;

public class LateCheckOut extends AbstractQuery<List<Customer>>{

	public LateCheckOut(String date, String managerID) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Customer> parseResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
