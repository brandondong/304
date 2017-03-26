package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.IQuery;
import ui.QueryControl;

public class RoomAmenitiesComponent extends AbstractQueryComponent<Object> {

	public RoomAmenitiesComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Room Number:  "), QueryControl.text("Enter Street: "),
				QueryControl.text("Enter House Number: "), QueryControl.text("Enter Postal Code: ") };
	}

	@Override
	protected IQuery<Object> createQuery(JFormattedTextField[] textFields) {
		int RoomNumber = (int) textFields[0].getValue();
		String Street = textFields[1].getText();
		String HouseNumber = textFields[2].getText();
		String PostalCode = textFields[3].getText();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Query Room Amenities";
	}

	@Override
	protected List<List<String>> parseData(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
