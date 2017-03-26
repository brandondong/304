package components;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.CustomerInfo;
import queries.IQuery;
import ui.QueryControl;

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
