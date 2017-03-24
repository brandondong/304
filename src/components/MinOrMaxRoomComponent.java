package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Room;
import queries.IQuery;
import queries.MinOrMaxPricedRoom;

public class MinOrMaxRoomComponent extends AbstractQueryComponent<List<Room>> {

	public MinOrMaxRoomComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter House Number: ", "Enter Street: ", "Enter Postal Code: ",
				"What you would like to select? (1 for highest, 0 for lowest)" };
	}

	@Override
	protected IQuery<List<Room>> createQuery(JTextField[] textFields) {
		String houseNo = textFields[0].getText();
		String street = textFields[1].getText();
		String postalCode = textFields[2].getText();
		boolean isMax = Integer.valueOf(textFields[3].getText()) == 1;
		return new MinOrMaxPricedRoom(isMax, street, houseNo, postalCode);
	}

}
