package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AggregateOperation;
import model.BranchLocation;

public class AggregatePriceByBranch extends AbstractQuery<List<BranchLocation>> {

	public AggregateOperation aggregation;

	public AggregatePriceByBranch(AggregateOperation a) {
		this.aggregation = a;
	}

	@Override
	protected List<BranchLocation> parseResult(ResultSet rs) throws SQLException {
		List<BranchLocation> locations = new ArrayList<>();
		while (rs.next()) {
			locations.add(new BranchLocation(rs.getString("Street"), rs.getString("HouseNumber"),
					rs.getString("PostalCode"), rs.getInt("AggregatePrice"), aggregation));
		}
		return locations;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format("SELECT Street, HouseNumber, PostalCode, %s(TotalCost) AS AggregatePrice "
				+ "FROM RentCost INNER JOIN Reservation ON RentCost.ConfirmationID = Reservation.ConfirmationID "
				+ "GROUP BY Street, HouseNumber, PostalCode", aggregation);
	}

}
