package components;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import queries.CheckOut;

public class CheckOutComponent extends AbstractQueryComponent<Integer> {

	public CheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Confirmation Number:  " };
	}

	@Override
	protected void executeQuery(JTextField[] textFields) {
		int ConfirmationID = Integer.valueOf(textFields[0].getText());
		
		CheckOut c = new CheckOut(ConfirmationID);
		
		try{
			Integer i = c.execute(con);
			mainFrame.dispose();
			displayData(i);
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected void displayData(Integer t){
		// TODO Auto-generated method stub
		
	}
	

//	@Override
//	protected IQuery<Void> createQuery(JTextField[] textFields) {
//		int ConfirmationID = Integer.valueOf(textFields[0].getText());
//		// TODO implement query
//		return null;
//	}

}
