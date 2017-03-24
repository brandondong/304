package main;

import java.sql.Connection;

import javax.swing.JFrame;

public class DisplayResult extends PerformQuery {

	public DisplayResult(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

}
