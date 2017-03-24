package components;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.CustomerInfo;
import queries.IQuery;

public class CustomerInfoComponent extends AbstractQueryComponent<Map<String, Object>> {

	public CustomerInfoComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Customer ID: ", "Retrieve name? (1 for yes, 0 for no)",
				"Retrieve phone #? (1 for yes, 0 for no)",
				"Retrieve payment method? (1 for yes, 0 for no)" };
	}

	@Override
	protected IQuery<Map<String, Object>> createQuery(JTextField[] textFields) {
		int CustomerID = Integer.valueOf(textFields[0].getText());
		boolean Name = Integer.valueOf(textFields[1].getText()) == 1;
		boolean PhoneNumber = Integer.valueOf(textFields[2].getText()) == 1;
		boolean PaymentMethod = Integer.valueOf(textFields[3].getText()) == 1;
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

}
