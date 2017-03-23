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
	protected JTextField CTTextFields[];
	protected JTextField MRTextFields[];
	protected JTextField COTextFields[];
	protected JTextField RATextFields[];
	protected JTextField RPTextFields[];
	protected JTextField LCOTextFields[];
	protected JTextField CITextFields[];
	protected JTextField CARTextFields[];
	protected JTextField MLTextFields[];
	protected JTextField APTextFields[];
	
	public CollectInput(Connection con) {
		super(con);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		PerformQuery p = new PerformQuery(con);
		if (cmd.equals("Reserve Room Finish")){
			if (checkForNull(RRTextFields)){
				ReserveRoom();
			}
			else
				p.ReserveRoomExecute();
		 }
		else if (cmd.equals("Check In Finish")){
			if (checkForNull(CTTextFields))
				 CheckIn();
			else
				p.CheckInExecute();			
		}
		else if (cmd.equals("Modify Reservation Finish")){
			if (checkForNull(MRTextFields))
				 ModifyReservation();
			else
				p.ModifyReservationExecute();					
		}
		else if (cmd.equals("Check Out Finish")){
			if (checkForNull(COTextFields))
				 CheckOut();
			else
				p.CheckOutExecute();			
		}
		else if (cmd.equals("Room Amenities Finish")){
			if (checkForNull(RATextFields))
				 RoomAmenities();
			else
				p.RoomAmenitiesExecute();				
		}
		else if (cmd.equals("Room Price Finish")){
			if (checkForNull(RPTextFields))
				 RoomPrice();
			else
				p.RoomPriceExecute();	
		}
		else if (cmd.equals("Late Check Out Finish")){
			if (checkForNull(LCOTextFields))
				 LateCheckOut();
			else
				p.LateCheckOutExecute();	
		}
		else if (cmd.equals("Cust Info Finish")){
			if (checkForNull(CITextFields))
				 CustInfo();
			else
				p.CustInfoExecute();				
		}
		else if (cmd.equals("Cust All Finish")){
			if (checkForNull(CARTextFields))
				 CustAllRoom();
			else
				p.CustAllRoomExecute();	
		}
		else if (cmd.equals("ML Finish")){
			if (checkForNull(MLTextFields))
				 ML();
			else
				p.MLExecute();				
		}
		else if (cmd.equals("AP Finish")){
			if (checkForNull(APTextFields))
				 AggrPrice();
			else
				p.AggrPriceExecute();	
		}
	}

	private boolean checkForNull(JTextField j[]){
		 for (int i = 0; i < j.length; i++){
			 if (j[i] < 0)
				 return true;
		 }		
		 return false;
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

		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2,
				6, 6, 
				6, 6); 

		j[0].requestFocus();
		finish.addActionListener(this);
		finish.setActionCommand(buttonTag);	
		return j;
	}
	
	public void ReserveRoom() {
		String[] labels = { "Enter Start Date (yyyymmdd)", "Enter End Date (yyyymmdd): ",
				"Enter Room Number: ", "Enter Street: ", "Enter House Number: ",
				"Enter Postal Code: ", "Enter Customer Id: " };
		RRTextFields = makeGrid(labels, "Reserve Room Finish");

	}
	
	public void CheckIn(){
		String[] labels = {"Enter Confirmation Number: ", "Enter Customer ID: "};
		CTTextFields = makeGrid(labels, "Check In Finish");
	}
	
	public void ModifyReservation(){
		String[] labels = {"Enter New Check-In Date (yyyymmdd): ", "Enter New Check-out Date (yyyymmdd): ",
				"Enter Confirmation Number: "};
		MRTextFields = makeGrid(labels, "Modify Reservation Finish");
	}
	
	public void CheckOut() {
		String[] labels = {"Enter Confirmation Number:  "};
		COTextFields = makeGrid(labels, "Check Out Finish");
	}

	public void RoomAmenities() {
		String[] labels = {"Enter Room Number:  ", "Enter Street: ", "Enter House Number: ", "Enter Postal Code: "};
		RATextFields = makeGrid(labels, "Room Amenities Finish");
	}

	public void RoomPrice() {
		String[] labels = {"Enter Price Threshold:  ", "Enter 1 for Above, or 0 for Below: ", "Enter Street: ", "Enter House Number: ", "Enter Postal Code: "};
		RPTextFields = makeGrid(labels, "Room Price Finish");
	}

	public void LateCheckOut() {
		String[] labels = {"Enter Current Date: ", "Enter Manager ID: "};
		LCOTextFields = makeGrid(labels, "Late Check Out Finish");
	}

	public void CustInfo() {
		String [] labels = {"Enter Customer ID: ", "Do you want to retrive the customer's name? (1 for yes, 0 for no)",
				"Do you want to retrive the customer's phone number? (1 for yes, 0 for no)",
				"Do you want to retrive the customer's payment method? (1 for yes, 0 for no)"};
		CITextFields = makeGrid(labels, "Cust Info Finish");
	}

	public void CustAllRoom() {
		String[] labels = {"Enter House Number: ", "Enter Street: ", "Enter Postal Code: "};
		CARTextFields = makeGrid(labels, "Cust All Finish");
	}

	public void ML() {
		String[] labels = {"Enter House Number: ", "Enter Street: ", "Enter Postal Code: ", "What you would like to select? (1 for highest, 0 for lowest)"};
		MLTextFields = makeGrid(labels, "ML Finish");
	}

	public void AggrPrice() {
		String[] labels = {"Enter House Number: ", "Enter Street: ", "Enter Postal Code: ", 
				"What you would like to aggregate by? (MAX, MIN, COUNT, AVG)"};
		APTextFields = makeGrid(labels, "AP Finish");	
	}
}
