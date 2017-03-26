package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFormattedTextField;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import model.AggregateOperation;
import model.BranchLocation;
import queries.AggregatePriceByBranch;
import queries.IQuery;
import ui.QueryControl;

public class AggregateBranchPriceComponent extends AbstractQueryComponent<List<BranchLocation>> {

	public AggregateBranchPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("What would you like to aggregate by? (MAX, MIN, COUNT, AVG)") };
	}

	@Override
	protected IQuery<List<BranchLocation>> createQuery(JFormattedTextField[] textFields) {
		AggregateOperation aggregate = AggregateOperation.valueOf(textFields[0].getText());
		return new AggregatePriceByBranch(aggregate);
	}

	@Override
	public String getDescription() {
		return "Query Aggregated Prices In Branch";
	}

	@Override
	protected List<List<String>> parseData(List<BranchLocation> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("Street", "HouseNumber", "PostalCode", "AggregatedPrice"));
		System.out.println("size: "+ Integer.toString(t.size()));
		for (int i = 0; i < t.size(); i++){
			BranchLocation b = t.get(i);
			data.add(Arrays.asList(b.getStreet(), b.getHouseNumber(), b.getPostalCode(), Integer.toString(b.getAggregatedPrice())));
		}
		return data;
	}

}
