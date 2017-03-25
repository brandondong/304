package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.IQuery;
import ui.QueryControl;

public class LateCheckOutComponent extends AbstractQueryComponent<Object> {

	public LateCheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.date("Enter Current Date (yyyy-mm-dd): "),
				QueryControl.text("Enter Manager ID: ") };
	}

	@Override
	protected void displayData(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected IQuery<Object> createQuery(JFormattedTextField[] textFields) {
		String Date = textFields[0].getText();
		String ManagerID = textFields[1].getText();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Query Late Check Outs";
	}

}
