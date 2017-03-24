package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.IQuery;

public class CheckOutComponent extends AbstractQueryComponent<Void> {

	public CheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Confirmation Number:  " };
	}

	@Override
	protected IQuery<Void> createQuery(JTextField[] textFields) {
		int ConfirmationID = Integer.valueOf(textFields[0].getText());
		// TODO implement query
		return null;
	}

}
