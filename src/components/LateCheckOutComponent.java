package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.IQuery;

public class LateCheckOutComponent extends AbstractQueryComponent<Object> {

	public LateCheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Current Date: ", "Enter Manager ID: " };
	}

	@Override
	protected IQuery<Object> createQuery(JTextField[] textFields) {
		String Date = textFields[0].getText();
		String ManagerID = textFields[1].getText();
		// TODO implement query
		return null;
	}

}
