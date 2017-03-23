package main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//We need to import the java.sql package to use JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//for the login window
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ui.SpringUtilities;

/*
* This class implements a graphical login window and a simple text
* interface for interacting with the branch table 
*/
public class Application implements ActionListener {

	/**
	 * Set to <code>true</code> for development at home through Xmanager
	 */
	private static final boolean USE_LOCAL = true;

	private Connection con;

	// user is allowed 3 login attempts
	private int loginAttempts = 0;

	// components of the login window
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private final JFrame mainFrame;

	// components of menu window
	// private final JFrame menuFrame;

	/*
	 * constructs login window and loads JDBC driver
	 */
	public Application() {
		mainFrame = new JFrame("Hotel Application");

		JLabel usernameLabel = new JLabel("Enter username: ");
		JLabel passwordLabel = new JLabel("Enter password: ");

		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');

		JButton loginButton = new JButton("Log In");

		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);

		// layout components using the GridBag layout manager

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// place the username label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(usernameLabel, c);
		contentPane.add(usernameLabel);

		// place the text field for the username
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);
		gb.setConstraints(usernameField, c);
		contentPane.add(usernameField);

		// place password label
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 10, 10, 0);
		gb.setConstraints(passwordLabel, c);
		contentPane.add(passwordLabel);

		// place the password field
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(0, 0, 10, 10);
		gb.setConstraints(passwordField, c);
		contentPane.add(passwordField);

		// place the login button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(loginButton, c);
		contentPane.add(loginButton);

		// register password field and OK button with action event handler
		passwordField.addActionListener(this);
		loginButton.addActionListener(this);
		loginButton.setActionCommand("Log");

		// anonymous inner class for closing the window
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// size the window to obtain a best fit for the components
		mainFrame.pack();

		// center the frame
		Dimension d = mainFrame.getToolkit().getScreenSize();
		Rectangle r = mainFrame.getBounds();
		mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		// make the window visible
		mainFrame.setVisible(true);

		// place the cursor in the text field for the username
		usernameField.requestFocus();

		try {
			// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException ex) {
			// TODO probably display error dialog to user before exiting?
			System.exit(-1);
		}
	}

	/*
	 * connects to Oracle database named ug using user supplied username and
	 * password
	 */
	private boolean connect(String username, String password) {
		String connectURL;
		if (USE_LOCAL)
			connectURL = "jdbc:oracle:thin:@localhost:1522:ug";
		else
			connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

		try {
			con = DriverManager.getConnection(connectURL, "ora_c7l0b", "a30032122");

			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException ex) {
			// TODO display error in UI
			return false;
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Log")) {
			if (connect(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
				mainFrame.dispose();
				showMenu();
			} else {
				loginAttempts++;

				if (loginAttempts >= 3) {
					mainFrame.dispose();
					System.exit(-1);
				} else {
					// clear the password
					passwordField.setText("");
				}
			}
		} else if (cmd.equals("Reserve Room")) {
			System.out.println("a");
			ReserveRoom();
		} else if (cmd.equals("Check In")) {
			CheckIn();
		} else if (cmd.equals("Modify Reservation")) {
			ModifyReservation();
		} else if (cmd.equals("Check Out")) {
			CheckOut();
		} else if (cmd.equals("Room Amenities")) {
			RoomAmenities();
		} else if (cmd.equals("Room Price")) {
			RoomPrice();
		} else if (cmd.equals("Late Check Out")) {
			LateCheckOut();
		} else if (cmd.equals("Cust Info")) {
			CustInfo();
		} else if (cmd.equals("Cust All Room")) {
			CustAllRoom();
		} else if (cmd.equals("ML")) {
			ML();
		} else if (cmd.equals("Aggr Price")) {
			AggrPrice();
		}

	}

	private void showMenu() {
		try {
			con.setAutoCommit(false);

			JButton ReserveRoom = new JButton("Reserve Room");
			JButton CheckIn = new JButton("Check In");
			JButton ModifyReservation = new JButton("Modify Reservation");
			JButton CheckOut = new JButton("Check Out");
			JButton RoomAmenities = new JButton("Query Room Amenities");
			JButton RoomPrice = new JButton("Query Room Above/Below Price");
			JButton LateCheckOut = new JButton("Query Late Check Outs");
			JButton CustInfo = new JButton("Query Customer Information");
			JButton CustAllRoom = new JButton("Query Customer Who Reserved All Rooms In Branch");
			JButton MLExpensive = new JButton("Query Most/Least Expensive Room In Branch");
			JButton AggrPrice = new JButton("Query Aggregated Prices In Branch");

			JPanel contentPane = new JPanel();
			mainFrame.setContentPane(contentPane);

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();

			contentPane.setLayout(gb);
			contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			c.gridwidth = GridBagConstraints.REMAINDER;
			c.insets = new Insets(5, 10, 10, 10);
			c.anchor = GridBagConstraints.CENTER;
			gb.setConstraints(ReserveRoom, c);
			contentPane.add(ReserveRoom);
			gb.setConstraints(CheckIn, c);
			contentPane.add(CheckIn);
			gb.setConstraints(ModifyReservation, c);
			contentPane.add(ModifyReservation);
			gb.setConstraints(CheckOut, c);
			contentPane.add(CheckOut);
			gb.setConstraints(RoomAmenities, c);
			contentPane.add(RoomAmenities);
			gb.setConstraints(RoomPrice, c);
			contentPane.add(RoomPrice);
			gb.setConstraints(LateCheckOut, c);
			contentPane.add(LateCheckOut);
			gb.setConstraints(CustInfo, c);
			contentPane.add(CustInfo);
			gb.setConstraints(CustAllRoom, c);
			contentPane.add(CustAllRoom);
			gb.setConstraints(MLExpensive, c);
			contentPane.add(MLExpensive);
			gb.setConstraints(AggrPrice, c);
			contentPane.add(AggrPrice);

			ReserveRoom.setActionCommand("Reserve Room");
			CheckIn.setActionCommand("Check In");
			ModifyReservation.setActionCommand("Modify Reservation");
			CheckOut.setActionCommand("Check Out");
			RoomAmenities.setActionCommand("Room Amenities");
			RoomPrice.setActionCommand("Room Price");
			LateCheckOut.setActionCommand("Late Check Out");
			CustInfo.setActionCommand("Cust Info");
			CustAllRoom.setActionCommand("Cust All Room");
			MLExpensive.setActionCommand("ML");
			AggrPrice.setActionCommand("Aggr Price");

			ReserveRoom.addActionListener(this);
			mainFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			mainFrame.pack();

			Dimension d = mainFrame.getToolkit().getScreenSize();
			Rectangle r = mainFrame.getBounds();
			mainFrame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

			mainFrame.setVisible(true);

		} catch (SQLException e) {
			// TODO handle appropriately in UI (error dialog?)
		}
	}

	public static void main(String args[]) {
		new Application();
	}

	public String toDateString(int day, int month, int year) {
		return String.format("%d%d%d", year, month, day);
	}

	public void ReserveRoom() {
		
		String[] labels = {"Enter start day (2 digits): ", "Enter start month (2 digits): ", "Enter start year (4 digits): ", "Enter end day (2 digits): ",
				"Enter end month (2 digits): ", "Enter end year (2 digits): ", "Enter room number: ", "Enter street: ", 
				"Enter house number: ", "Enter postal code: ", "Enter customer id: "};
		int numPairs = labels.length;

		// mainFrame.pack();
		 Insets insets = mainFrame.getInsets();
		 mainFrame.setSize(new Dimension(insets.left + insets.right + 500,
		             insets.top + insets.bottom + 500));
		 
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

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(p,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		
		j[0].requestFocus();
	}

	public void CheckIn() {
	}

	public void ModifyReservation() {
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
