package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.AggregateOperation;
import model.BranchLocation;
import queries.AggregatePriceByBranch;
import queries.IQuery;

public class AggregateBranchPriceComponent extends AbstractQueryComponent<List<BranchLocation>> {

	public AggregateBranchPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "What you would like to aggregate by? (MAX, MIN, COUNT, AVG)" };
	}

	@Override
	protected IQuery<List<BranchLocation>> createQuery(JTextField[] textFields) {
		AggregateOperation aggregate = AggregateOperation.valueOf(textFields[0].getText());
		return new AggregatePriceByBranch(aggregate);
	}

}
