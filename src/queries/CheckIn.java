package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIn extends AbstractQuery<Void> {

	private final int confirmID;

	private final int cost;

	public CheckIn(int confirmID, int cost, Connection con) {
		super(con);
		this.confirmID = confirmID;
		this.cost = cost;
	}

	@Override
	protected Void parseResult(ResultSet rs) throws SQLException {
		return null;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format("INSERT into RentCost values (%d, %d)", confirmID, cost);
	}

}
