package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.AbstractQueryComponent;
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

public class MainMenu {

	private final Connection con;

	private final JFrame mainFrame;

	public MainMenu(Connection con, JFrame mainFrame) {
		this.con = con;
		this.mainFrame = mainFrame;
	}

	public void showMenu() {
		JPanel contentPane = new JPanel();
		mainFrame.setContentPane(contentPane);
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(5, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;

		for (AbstractQueryComponent<?> comp : getComponents(con, mainFrame)) {
			JButton button = new JButton(comp.getDescription());
			gb.setConstraints(button, c);
			contentPane.add(button);
			button.addActionListener((e) -> {
				mainFrame.dispose();
				comp.render();
			});
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
	}

	private AbstractQueryComponent<?>[] getComponents(Connection con, JFrame mainFrame) {
		return new AbstractQueryComponent<?>[] { new ReserveRoomComponent(con, mainFrame),
				new CheckInComponent(con, mainFrame), new ModifyReservationComponent(con, mainFrame),
				new CheckOutComponent(con, mainFrame), new RoomAmenitiesComponent(con, mainFrame),
				new RoomPriceComponent(con, mainFrame), new LateCheckOutComponent(con, mainFrame),
				new CustomerInfoComponent(con, mainFrame), new CustomerAllRoomsComponent(con, mainFrame),
				new MinOrMaxRoomComponent(con, mainFrame), new AggregateBranchPriceComponent(con, mainFrame) };
	}

}
