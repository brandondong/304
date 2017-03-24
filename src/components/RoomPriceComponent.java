package components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Room;
import queries.IQuery;
import ui.SpringUtilities;

public class RoomPriceComponent extends AbstractQueryComponent<List<Room>> {

	public RoomPriceComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected String[] getLabels() {
		return new String[] { "Enter Price Threshold:  ", "Enter 1 for Above, or 0 for Below: ", "Enter Street: ",
				"Enter House Number: ", "Enter Postal Code: " };
	}

	@Override
	protected void displayData(List<Room> t) {
		int cols = 2;
		int rows = t.size();

		JPanel p = setUpLayout();

		JTextField title1 = new JTextField("RoomNumber");
		JTextField title2 = new JTextField("RoomPrice");
		p.add(title1);
		p.add(title2);

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JTextField textField = new JTextField((r == 0) ? t.get(c).getRoomNumber() : t.get(c).getRoomPrice());
				p.add(textField);
			}
		}

		JButton returnB = new JButton("Return to Menu");
		p.add(returnB);
		returnB.addActionListener(this);
		returnB.setActionCommand("Return");

		SpringUtilities.makeCompactGrid(p, rows + 2, cols, 3, 3, 3, 3);

		mainFrame.pack();
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);
		mainFrame.setVisible(true);
	}

	@Override
	protected IQuery<List<Room>> createQuery(JTextField[] textFields) {
		int Price = Integer.valueOf(textFields[0].getText());
		boolean Above = Integer.valueOf(textFields[1].getText()) == 1;
		String Street = textFields[2].getText();
		// TODO implement query
		return null;
	}

	@Override
	public String getDescription() {
		return "Query Room Above/Below Price";
	}

}
