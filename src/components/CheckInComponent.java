package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.CheckIn;
import queries.IQuery;
import ui.QueryControl;

public class CheckInComponent extends AbstractQueryComponent<Void> {

	public CheckInComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Confirmation Number: "),
				QueryControl.integer("Enter Customer ID: "), QueryControl.integer("Enter Rental Cost: ") };
	}

	@Override
	protected void displayData(Void t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Void> createQuery(JFormattedTextField[] textFields) {
		int confirmID = (int) textFields[0].getValue();
		int custID = (int) textFields[1].getValue();
		int cost = (int) textFields[2].getValue();
		return new CheckIn(confirmID, custID, cost);
	}

	@Override
	public String getDescription() {
		return "Check In";
	}

}