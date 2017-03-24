package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.AggregateBranchPriceComponent;
import components.CheckInComponent;
import components.CheckOutComponent;
import components.CustomerAllRoomsComponent;
import components.CustomerInfoComponent;
import components.LateCheckOutComponent;
import components.MinOrMaxRoomComponent;
import components.ModifyReservationComponent;
import components.ReserveRoomComponent;
import components.RoomAmenitiesComponent;
import components.RoomPriceComponent;

public class MainMenu implements ActionListener {

	private final Connection con;

	private final JFrame mainFrame;

	private final String tags[] = { "Reserve Room", "Check In", "Modify Reservation", "Check Out",
			"Query Room Amenities", "Query Room Above/Below Price", "Query Late Check Outs",
			"Query Customer Information", "Query Customer Who Reserved All Rooms In Branch",
			"Query Most/Least Expensive Room In Branch", "Query Aggregated Prices In Branch" };

	public MainMenu(Connection con, JFrame mainFrame) {
		this.con = con;
		this.mainFrame = mainFrame;
	}

	public void showMenu() {
		try {
			con.setAutoCommit(false);

			int numTags = tags.length;
			JButton buttons[] = new JButton[numTags];

			JPanel contentPane = new JPanel();
			mainFrame.setContentPane(contentPane);
			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			contentPane.setLayout(gb);
			contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.insets = new Insets(5, 10, 10, 10);
			c.anchor = GridBagConstraints.CENTER;

			for (int i = 0; i < numTags; i++) {
				buttons[i] = new JButton(tags[i]);
				gb.setConstraints(buttons[i], c);
				contentPane.add(buttons[i]);
				buttons[i].setActionCommand(tags[i]);
				buttons[i].addActionListener(this);
			}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(tags[0])) {
			mainFrame.dispose();
			new ReserveRoomComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[1])) {
			mainFrame.dispose();
			new CheckInComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[2])) {
			mainFrame.dispose();
			new ModifyReservationComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[3])) {
			mainFrame.dispose();
			new CheckOutComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[4])) {
			mainFrame.dispose();
			new RoomAmenitiesComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[5])) {
			mainFrame.dispose();
			new RoomPriceComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[6])) {
			mainFrame.dispose();
			new LateCheckOutComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[7])) {
			mainFrame.dispose();
			new CustomerInfoComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[8])) {
			mainFrame.dispose();
			new CustomerAllRoomsComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[9])) {
			mainFrame.dispose();
			new MinOrMaxRoomComponent(con, mainFrame).render();
		} else if (cmd.equals(tags[10])) {
			mainFrame.dispose();
			new AggregateBranchPriceComponent(con, mainFrame).render();
		}
	}

}
