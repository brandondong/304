package components;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import model.Room;
import queries.IQuery;
import queries.MinOrMaxPricedRoom;
import ui.QueryControl;

public class MinOrMaxRoomComponent extends AbstractQueryComponent<List<Room>> {

	public MinOrMaxRoomComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.text("Enter House Number: "), QueryControl.text("Enter Street: "),
				QueryControl.text("Enter Postal Code: "),
				QueryControl.integer("What you would like to select? (1 for highest, 0 for lowest)") };
	}

	@Override
	protected IQuery<List<Room>> createQuery(JFormattedTextField[] textFields) {
		String houseNo = textFields[0].getText();
		String street = textFields[1].getText();
		String postalCode = textFields[2].getText();
		boolean isMax = (int) textFields[3].getValue() == 1;
		return new MinOrMaxPricedRoom(isMax, street, houseNo, postalCode);
	}

	@Override
	public String getDescription() {
		return "Query Most/Least Expensive Room In Branch";
	}

	@Override
	protected List<List<String>> parseData(List<Room> t) {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList("Room Number", "Room Price"));
		for (int i = 0; i < t.size(); i++){
			Room b = t.get(i);
			data.add(Arrays.asList(Integer.toString(b.getRoomNumber()), Integer.toString(b.getRoomPrice())));
		}
		return data;
	}

}
