package components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Room;
import queries.IQuery;
import ui.QueryControl;
import ui.SpringUtilities;

public class RoomPriceComponent extends AbstractQueryComponent<List<Room>> {

	public RoomPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Price Threshold:  "),
				QueryControl.integer("Enter 1 for Above, or 0 for Below: "), QueryControl.text("Enter Street: "),
				QueryControl.text("Enter House Number: "), QueryControl.text("Enter Postal Code: ") };
	}

	@Override
	protected IQuery<List<Room>> createQuery(JFormattedTextField[] textFields) {
		int Price = (int) textFields[0].getValue();
		boolean Above = (int) textFields[1].getValue() == 1;
		String Street = textFields[2].getText();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Query Room Above/Below Price";
	}

	@Override
	protected List<List<String>> parseData(List<Room> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
