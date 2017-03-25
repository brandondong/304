package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.CheckIn;
import queries.IQuery;

public class CheckInComponent extends AbstractQueryComponent<Void> {

	public CheckInComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Confirmation Number: ", "Enter Customer ID: ", "Enter Rental Cost: " };
	}

	@Override
	protected void displayData(Void t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Void> createQuery(JTextField[] textFields) {
		int confirmID = Integer.valueOf(textFields[0].getText());
		int custID = Integer.valueOf(textFields[1].getText());
		int cost = Integer.valueOf(textFields[2].getText());
		return new CheckIn(confirmID, custID, cost);
	}

}