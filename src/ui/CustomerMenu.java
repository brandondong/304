package ui;

import java.sql.Connection;

import javax.swing.JFrame;

import components.AbstractQueryComponent;
import components.CheckInComponent;
import components.CheckOutComponent;
import components.ModifyReservationComponent;
import components.ReserveRoomComponent;
import components.RoomAmenitiesComponent;
import components.RoomPriceComponent;

public class CustomerMenu extends AbstractMenu{

	public CustomerMenu(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}
	
	@Override
	protected AbstractQueryComponent<?>[] getComponents(Connection con, JFrame mainFrame) {
		return new AbstractQueryComponent<?>[] { new ReserveRoomComponent(con, mainFrame),
				new CheckInComponent(con, mainFrame), new ModifyReservationComponent(con, mainFrame),
				new CheckOutComponent(con, mainFrame), new RoomAmenitiesComponent(con, mainFrame),
				new RoomPriceComponent(con, mainFrame)};
	}
}
