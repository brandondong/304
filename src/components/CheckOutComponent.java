package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.IQuery;
import ui.QueryControl;

public class CheckOutComponent extends AbstractQueryComponent<Integer> {

	public CheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Confirmation Number:  ") };
	}

	@Override
	protected void displayData(Integer t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Integer> createQuery(JFormattedTextField[] textFields) {
		int ConfirmationID = (int) textFields[0].getValue();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Check Out";
	}

}
