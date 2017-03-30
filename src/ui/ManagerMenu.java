package ui;

import java.sql.Connection;

import javax.swing.JFrame;

import components.AbstractQueryComponent;
import components.AggregateBranchPriceComponent;
import components.CustomerAllRoomsComponent;
import components.CustomerInfoComponent;
import components.LateCheckOutComponent;
import components.MinOrMaxRoomComponent;
import components.ModifyReservationComponent;
import components.RoomAmenitiesComponent;
import components.RoomPriceComponent;

public class ManagerMenu extends AbstractMenu{

	public ManagerMenu(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}
	
	@Override
	protected AbstractQueryComponent<?>[] getComponents(Connection con, JFrame mainFrame) {
		return new AbstractQueryComponent<?>[] {
				new ModifyReservationComponent(con, mainFrame),
				new RoomAmenitiesComponent(con, mainFrame),
				new RoomPriceComponent(con, mainFrame), new LateCheckOutComponent(con, mainFrame),
				new CustomerInfoComponent(con, mainFrame), new CustomerAllRoomsComponent(con, mainFrame),
				new MinOrMaxRoomComponent(con, mainFrame), new AggregateBranchPriceComponent(con, mainFrame) };
	}

}
