package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BranchLocation;

public class AggregatePriceByBranch extends AbstractQuery<List<BranchLocation>>{
	
	public static final int COUNT = 0;
	public static final int MAX = 1;
	public static final int MIN = 2;
	public static final int AVG = 3;
	
	public String aggregation;
	
	public AggregatePriceByBranch(int a) {
		this.aggregation = (a == COUNT)? "COUNT" : (a == MAX) ? "MAX" : (a == MIN) ? "MIN" : "AVG";
	}

	@Override
	protected List<BranchLocation> parseResult(ResultSet rs) throws SQLException {
		List<BranchLocation> locations = new ArrayList<>();
		while (rs.next()) {
			locations.add(new BranchLocation(rs.getString("PostalCode"), rs.getInt("AggregatePrice"), aggregation));
		}
		return locations;
	}


	@Override
	protected String getQueryDefinition() {
		return String.format(
				"SELECT l.PostalCode, %s(*) AS AggregatePrice FROM BranchLocation l, Branch b, Room r, RoomType t "
						+ "WHERE l.PostalCode = b.PostalCode AND b.HouseNumber = r.HouseNumber "
						+ "AND r.TypeName = t.TypeName "
						+ "GROUP BY l.PostalCode",
				aggregation);
	}

}
