package components;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import model.Room;
import queries.IQuery;
import queries.RoomAboveOrBelowPrice;
import ui.QueryControl;

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
		String HouseNumber = textFields[3].getText();
		String PostalCode = textFields[4].getText();
		return new RoomAboveOrBelowPrice(Price, Above, Street, HouseNumber, PostalCode);
	}

	@Override
	public String getDescription() {
		return "Query Room Above/Below Price";
	}

	@Override
	protected List<List<String>> parseData(List<Room> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("RoomNumber", "RoomPrice"));
		System.out.println("size: "+ Integer.toString(t.size()));
		for (int i = 0; i < t.size(); i++){
			Room b = t.get(i);
			data.add(Arrays.asList(Integer.toString(b.getRoomNumber()), Integer.toString(b.getRoomPrice())));
		}
		return data;
	}

}
