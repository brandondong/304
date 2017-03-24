package components;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.RoomWithAmenities;
import queries.RoomAmenities;

public class RoomAmenitiesComponent extends AbstractQueryComponent<Object> {

	public RoomAmenitiesComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Room Number:  ", "Enter Street: ", "Enter House Number: ", "Enter Postal Code: " };
	}

//	@Override
//	protected IQuery<Object> createQuery(JTextField[] textFields) {
//		int RoomNumber = Integer.valueOf(textFields[0].getText());
//		String Street = textFields[1].getText();
//		String HouseNumber = textFields[2].getText();
//		String PostalCode = textFields[3].getText();
//		// TODO implement query
//		return null;
//	}

	@Override
	protected void executeQuery(JTextField[] textFields) {
		int RoomNumber = Integer.valueOf(textFields[0].getText());
		String Street = textFields[1].getText();
		String HouseNumber = textFields[2].getText();
		String PostalCode = textFields[3].getText();
		
		RoomAmenities c = new RoomAmenities(RoomNumber, Street, HouseNumber, PostalCode);
		
		try{
			RoomWithAmenities r = c.execute(con);
			mainFrame.dispose();
			displayData(r);
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected void displayData(Object t) {
		// TODO Auto-generated method stub
		
	}

}
