package components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import queries.CustomerInfo;
import queries.IQuery;
import ui.QueryControl;

public class CustomerInfoComponent extends AbstractQueryComponent<Map<String, String>> {
	JCheckBox nameButton;
	JCheckBox phoneButton;
	JCheckBox payButton;
	
	public CustomerInfoComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Customer ID: ")};
	}

	@Override
	protected IQuery<Map<String, String>> createQuery(JFormattedTextField[] textFields) {
		int CustomerID = (int) textFields[0].getValue();
		List<String> selection = new ArrayList<>();
		
		if (nameButton.isSelected()){
			selection.add("Name");
		}
		if (phoneButton.isSelected()){
			selection.add("PhoneNumber");
		}
		if (payButton.isSelected()){
			selection.add("PaymentMethod");
		}
		return new CustomerInfo(CustomerID, selection.toArray(new String[selection.size()]));
	}

	@Override
	protected JFormattedTextField[] makeGrid(QueryControl[] fields){

		JFormattedTextField j[] = new JFormattedTextField[1];
		
		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		JPanel p = new JPanel(new GridLayout(0, 2));
		mainFrame.setContentPane(p);
	    
		JLabel l = new JLabel(fields[0].getLabel(), JLabel.TRAILING);
		p.add(l);
		JFormattedTextField textField = fields[0].getField();
		j[0] = textField;
		l.setLabelFor(textField);
		p.add(textField);
		
		JLabel k = new JLabel("What would you like to retrieve?");
		p.add(k);
		
		nameButton = new JCheckBox("Name");
	    phoneButton = new JCheckBox("Phone Number");
	    payButton = new JCheckBox("Payment Method");
	    
	    nameButton.setSelected(true);
	    phoneButton.setSelected(true);
	    payButton.setSelected(true);
	    
	    p.add(nameButton);
		k = new JLabel(" ");
		p.add(k);
	    p.add(phoneButton);
		k = new JLabel(" ");
		p.add(k);
	    p.add(payButton);
	    
	    nameButton.addActionListener(this);
	    phoneButton.addActionListener(this);
	    payButton.addActionListener(this);
        
		k = new JLabel(" ");
		p.add(k);
		JButton finish = new JButton("Finish");
		p.add(finish);
		finish.addActionListener(this);
		finish.setActionCommand("Finish");
		
		k = new JLabel(" ");
		p.add(k);
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
        
		return j;
	}
	
	@Override
	protected List<List<String>> parseData(Map<String, String> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> titles = new ArrayList<String>();
		titles.addAll(t.keySet());
		data.add(titles);
		List<String> entries = new ArrayList<String>();
		for (int i = 0; i < titles.size(); i++)
			entries.add(t.get(titles.get(i)));
		data.add(entries);
		return data;
	}

	@Override
	public String getDescription() {
		return "Query Customer Information";
	}
}
