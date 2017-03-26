package components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.AggregateOperation;
import model.BranchLocation;
import queries.AggregatePriceByBranch;
import queries.IQuery;
import ui.QueryControl;

public class AggregateBranchPriceComponent extends AbstractQueryComponent<List<BranchLocation>> {
	JRadioButton MaxButton;
    JRadioButton MinButton;
    JRadioButton AvgButton;
    JRadioButton CountButton;
    
	public AggregateBranchPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("What would you like to aggregate by?") };
	}

	@Override
	protected IQuery<List<BranchLocation>> createQuery(JFormattedTextField[] textFields) {
		AggregateOperation aggregate = MaxButton.isSelected() ? AggregateOperation.MAX : MinButton.isSelected() ? 
				AggregateOperation.MIN : AvgButton.isSelected() ? AggregateOperation.AVG : AggregateOperation.COUNT;
		return new AggregatePriceByBranch(aggregate);
	}

	@Override
	public String getDescription() {
		return "Query Aggregated Prices In Branch";
	}
	
	@Override
	protected JFormattedTextField[] makeGrid(QueryControl[] fields){

		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		mainFrame.setVisible(true);
		JPanel p = new JPanel(new GridLayout(0, 1));
		mainFrame.setContentPane(p);
	    
		JLabel l = new JLabel(fields[0].getLabel());
		
		MaxButton = new JRadioButton("MAX");
	    MinButton = new JRadioButton("MIN");
	    AvgButton = new JRadioButton("AVG");
	    CountButton = new JRadioButton("COUNT");
	    
	    CountButton.setSelected(true);
	    
		p.add(l);
	    p.add(CountButton);
	    p.add(MaxButton);
	    p.add(MinButton);
	    p.add(AvgButton);
        
	    ButtonGroup bg1 = new ButtonGroup( );
	    bg1.add(CountButton);
	    bg1.add(MaxButton);
	    bg1.add(MinButton);
	    bg1.add(AvgButton);
	    
	    MaxButton.addActionListener(this);
	    MinButton.addActionListener(this);
	    AvgButton.addActionListener(this);
	    CountButton.addActionListener(this);
        
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

	@Override
	protected boolean checkForNull(JTextField j[]) {
		return false;
	}
}
