package components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.AggregateOperation;
import model.BranchLocation;
import queries.AggregatePriceByBranch;
import queries.IQuery;
import ui.QueryControl;

public class AggregateBranchPriceComponent extends AbstractQueryComponent<List<BranchLocation>> {

	private JComboBox<AggregateOperation> firstAggregation;

	private JComboBox<AggregateOperation> secondAggregation;

	public AggregateBranchPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("What would you like to aggregate by?"),
				QueryControl.text("How would you like to further aggregate the results?") };
	}

	@Override
	protected IQuery<List<BranchLocation>> createQuery(JFormattedTextField[] textFields) {
		AggregateOperation first = (AggregateOperation) firstAggregation.getSelectedItem();
		AggregateOperation second = (AggregateOperation) secondAggregation.getSelectedItem();
		return new AggregatePriceByBranch(first, second);
	}

	@Override
	public String getDescription() {
		return "Query Aggregated Prices In Branch";
	}

	@Override
	protected JFormattedTextField[] makeGrid(QueryControl[] fields) {
		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		mainFrame.setVisible(true);
		JPanel p = new JPanel(new GridLayout(0, 1));
		mainFrame.setContentPane(p);

		JLabel l = new JLabel(fields[0].getLabel());
		p.add(l);
		firstAggregation = createCombo();
		p.add(firstAggregation);

		JLabel l2 = new JLabel(fields[1].getLabel());
		p.add(l2);
		secondAggregation = createCombo();
		p.add(secondAggregation);

		JButton finish = new JButton("Finish");
		p.add(finish);
		finish.addActionListener(this);
		finish.setActionCommand("Finish");

		JButton returnB = new JButton("Return to Menu");
		p.add(returnB);
		returnB.addActionListener(this);
		returnB.setActionCommand("Return");

		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainFrame.pack();
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);
		mainFrame.setVisible(true);

		return null;
	}

	private JComboBox<AggregateOperation> createCombo() {
		JComboBox<AggregateOperation> combo = new JComboBox<>();
		for (AggregateOperation a : AggregateOperation.values()) {
			combo.addItem(a);
		}
		return combo;
	}

	@Override
	protected List<List<String>> parseData(List<BranchLocation> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("Street", "HouseNumber", "PostalCode", "AggregatedPrice"));
		for (int i = 0; i < t.size(); i++) {
			BranchLocation b = t.get(i);
			data.add(Arrays.asList(b.getStreet(), b.getHouseNumber(), b.getPostalCode(),
					Integer.toString(b.getAggregatedPrice())));
		}
		return data;
	}

	@Override
	protected boolean checkForNull(JTextField j[]) {
		return false;
	}
}
