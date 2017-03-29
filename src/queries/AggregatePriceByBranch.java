package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AggregateOperation;
import model.BranchLocation;

public class AggregatePriceByBranch extends AbstractQuery<List<BranchLocation>> {

	private final AggregateOperation first;

	private final AggregateOperation second;

	public AggregatePriceByBranch(AggregateOperation first, AggregateOperation second) {
		this.first = first;
		this.second = second;

	}

	@Override
	protected List<BranchLocation> parseResult(ResultSet rs) throws SQLException {
		List<BranchLocation> locations = new ArrayList<>();
		while (rs.next()) {
			locations.add(new BranchLocation(rs.getString("Street"), rs.getString("HouseNumber"),
					rs.getString("PostalCode"), rs.getInt("AggregatePrice"), first));
		}
		return locations;
	}

	@Override
	protected String getQueryDefinition() {
		return String.format("SELECT Street, HouseNumber, PostalCode, %s(TotalCost) AS AggregatePrice "
				+ "FROM RentCost INNER JOIN Reservation ON RentCost.ConfirmationID = Reservation.ConfirmationID "
				+ "GROUP BY Street, HouseNumber, PostalCode", first);
	}

}
