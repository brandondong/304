package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ui.SpringUtilities;

public class CollectInput extends MainMenu{
	protected JTextField RRTextFields[];
	
	public CollectInput(Connection con) {
		super(con);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		PerformQuery p = new PerformQuery(con);
		if (cmd.equals("Reserve Room Finish")){
			 System.out.println("a");
			 for (int i = 0; i < RRTextFields.length; i++){
				 System.out.println("b");
				 if (RRTextFields[i] == null)
					 ReserveRoom();
			 }
			 System.out.println("c");
			 p.ReserveRoomExecute();
		 }
	}
	
	private JTextField[] makeGrid(String[] labels, String buttonTag){
		int numPairs = labels.length;
		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));

		JTextField j[] = new JTextField[numPairs];
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		mainFrame.setVisible(true);

		JPanel p = new JPanel(new SpringLayout());

		mainFrame.setContentPane(p);

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			JTextField textField = new JTextField(10);
			j[i] = textField;
			l.setLabelFor(textField);
			p.add(textField);
		}
		JLabel l = new JLabel("  ", JLabel.TRAILING);
		p.add(l);
		JButton finish = new JButton("Finish");
		l.setLabelFor(finish);
		p.add(finish);

		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		j[0].requestFocus();
		finish.addActionListener(this);
		finish.setActionCommand(buttonTag);	
		return j;
	}
	
	public void ReserveRoom() {
		String[] labels = { "Enter start day (2 digits): ", "Enter start month (2 digits): ",
				"Enter start year (4 digits): ", "Enter end day (2 digits): ", "Enter end month (2 digits): ",
				"Enter end year (2 digits): ", "Enter room number: ", "Enter street: ", "Enter house number: ",
				"Enter postal code: ", "Enter customer id: " };
		
		RRTextFields = makeGrid(labels, "Reserve Room Finish");

	}
	
	public void CheckIn(){
		
	}
	
	public void ModifyReservation(){
		
	}
	
	public void CheckOut() {
	}

	public void RoomAmenities() {
	}

	public void RoomPrice() {
	}

	public void LateCheckOut() {
	}

	public void CustInfo() {
	}

	public void CustAllRoom() {
	}

	public void ML() {
	}

	public void AggrPrice() {
	}
}
