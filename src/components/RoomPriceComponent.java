package components;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.IQuery;

public class RoomPriceComponent extends AbstractQueryComponent<Object> {

	public RoomPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Price Threshold:  ", "Enter 1 for Above, or 0 for Below: ", "Enter Street: ",
				"Enter House Number: ", "Enter Postal Code: " };
	}

	@Override
	protected IQuery<Object> createQuery(JTextField[] textFields) {
		int Price = Integer.valueOf(textFields[0].getText());
		boolean Above = Integer.valueOf(textFields[1].getText()) == 1;
		String Street = textFields[2].getText();
		// TODO implement query
		return null;
	}

}
