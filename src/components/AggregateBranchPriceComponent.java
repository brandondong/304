package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.AggregateOperation;
import model.BranchLocation;
import queries.AggregatePriceByBranch;
import queries.IQuery;
import ui.SpringUtilities;

public class AggregateBranchPriceComponent extends AbstractQueryComponent<List<BranchLocation>> {

	public AggregateBranchPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "What would you like to aggregate by? (MAX, MIN, COUNT, AVG)" };
	}

	@Override
	protected IQuery<List<BranchLocation>> createQuery(JTextField[] textFields) {
		AggregateOperation aggregate = AggregateOperation.valueOf(textFields[0].getText());
		return new AggregatePriceByBranch(aggregate);
	}

	@Override
	protected void displayData(List<BranchLocation> t) {
		int cols = 2;
		int rows = t.size();

		JPanel p = setUpLayout();

		JTextField title1 = new JTextField("RoomNumber");
		JTextField title2 = new JTextField("RoomPrice");
		p.add(title1);
		p.add(title2);

		// for (int r = 0; r < rows; r++) {
		// for (int c = 0; c < cols; c++) {
		// JTextField textField = new JTextField((r == 0) ?
		// t.get(c).getRoomNumber() : t.get(c).getRoomPrice());
		// p.add(textField);
		// }
		// }
		//
		// JButton returnB = new JButton("Return to Menu");
		// p.add(returnB);
		// returnB.addActionListener(this);
		// returnB.setActionCommand("Return");
		//
		SpringUtilities.makeCompactGrid(p, rows + 2, cols, 3, 3, 3, 3);
		//
		// mainFrame.pack();
		// Dimension d = mainFrame.getToolkit().getScreenSize();
		// Rectangle r = mainFrame.getBounds();
		// mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height)
		// / 2);
		// mainFrame.setVisible(true);
	}

	@Override
	public String getDescription() {
		return "Query Aggregated Prices In Branch";
	}
}
