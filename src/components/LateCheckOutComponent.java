package components;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Customer;
import queries.LateCheckOut;

public class LateCheckOutComponent extends AbstractQueryComponent<Object> {

	public LateCheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Current Date: ", "Enter Manager ID: " };
	}

	@Override
	protected void executeQuery(JTextField[] textFields) {
		String Date = textFields[0].getText();
		String ManagerID = textFields[1].getText();
		
		LateCheckOut c = new LateCheckOut(Date,ManagerID);
		try{
			List<Customer> r = c.execute(con);
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

//	@Override
//	protected IQuery<Object> createQuery(JTextField[] textFields) {
//		String Date = textFields[0].getText();
//		String ManagerID = textFields[1].getText();
//		// TODO implement query
//		return null;
//	}

}
