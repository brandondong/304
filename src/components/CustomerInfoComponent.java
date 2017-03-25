package components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import queries.CustomerInfo;
import queries.IQuery;
import ui.QueryControl;
import ui.SpringUtilities;

public class CustomerInfoComponent extends AbstractQueryComponent<Map<String, String>> {

	public CustomerInfoComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Customer ID: "),
				QueryControl.integer("Retrieve name? (1 for yes, 0 for no)"),
				QueryControl.integer("Retrieve phone #? (1 for yes, 0 for no)"),
				QueryControl.integer("Retrieve payment method? (1 for yes, 0 for no)") };
	}

	@Override
	protected IQuery<Map<String, String>> createQuery(JFormattedTextField[] textFields) {
		int CustomerID = (int) textFields[0].getValue();
		boolean Name = (int) textFields[1].getValue() == 1;
		boolean PhoneNumber = (int) textFields[2].getValue() == 1;
		boolean PaymentMethod = (int) textFields[3].getValue() == 1;
		List<String> selection = new ArrayList<>();
		if (Name) {
			selection.add("Name");
		}
		if (PhoneNumber) {
			selection.add("PhoneNumber");
		}
		if (PaymentMethod) {
			selection.add("PaymentMethod");
		}
		return new CustomerInfo(CustomerID, selection.toArray(new String[selection.size()]));
	}

	@Override
	protected void displayData(Map<String, String> t) {
		int cols = t.size();
		int rows = 2;

		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		mainFrame.setVisible(true);
		JPanel p = new JPanel(new SpringLayout());
		mainFrame.setContentPane(p);

		List<String> titles = new ArrayList<>();
		for (String key : t.keySet()) {
			titles.add(key);
			p.add(new JTextField(key));
		}

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JTextField textField = new JTextField(t.get(titles.get(r)));
				p.add(textField);
			}
		}

		JButton returnB = new JButton("Return to Menu");
		p.add(returnB);

		SpringUtilities.makeCompactGrid(p, rows + 2, cols, 3, 3, 3, 3);

		SpringLayout.Constraints contentPaneCons = layout.getConstraints(contentPane);
		contentPaneCons.setX(Spring.sum(Spring.constant(5), contentPaneCons.getConstraint(SpringLayout.WEST)));
		contentPaneCons.setY(Spring.sum(Spring.constant(5), contentPaneCons.getConstraint(SpringLayout.NORTH)));

		mainFrame.pack();
		returnB.addActionListener(this);
		returnB.setActionCommand("Return");
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);
		mainFrame.setVisible(true);
	}

	@Override
	public String getDescription() {
		return "Query Customer Information";
	}

}
