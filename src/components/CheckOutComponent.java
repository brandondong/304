package components;

import java.sql.Connection;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import queries.CheckOut;
import queries.IQuery;
import ui.QueryControl;

public class CheckOutComponent extends AbstractQueryComponent<Integer> {

	public CheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected QueryControl[] getFields() {
		return new QueryControl[] { QueryControl.integer("Enter Confirmation Number:  ") };
	}

	@Override
	protected IQuery<Integer> createQuery(JFormattedTextField[] textFields) {
		int ConfirmationID = (int) textFields[0].getValue();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Check Out";
	}

	protected void executeQuery(JTextField[] textFields) {
		int ConfirmationID = Integer.valueOf(textFields[0].getText());
		
		CheckOut c = new CheckOut(ConfirmationID);
		
		try{
			Integer i = c.execute(con);
			mainFrame.dispose();
			displayData(parseData(i));
		} catch (SQLException e) {
			mainFrame.dispose();
			render();
		}
	}

	@Override
	protected List<List<String>> parseData(Integer t)  {
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(Arrays.asList(Integer.toString(t)));
		return data;
	}
	
	@Override
	protected void displayData(List<List<String>> t){
		JPanel p = new JPanel(new SpringLayout());
		mainFrame.setContentPane(p);
		JTextField text = new JTextField("Successful check out. Cost = " + t.get(0).get(0));
		p.add(text);
		JButton returnB = new JButton("Return to Menu");
		p.add(returnB);
		returnB.addActionListener(this);
		returnB.setActionCommand("Return");
	}

}
