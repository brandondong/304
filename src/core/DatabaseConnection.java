package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

class DatabaseConnection {

	private static final String URL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

	private static final String LOCAL_URL = "jdbc:oracle:thin:@localhost:1522:ug";

	private static final IDatabaseCredentials CRDS = new DatabaseCredentials();

	/**
	 * Creates a connection to the CS Oracle database
	 */
	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(URL, CRDS.getUsername(), CRDS.getPassword());
	}

	/**
	 * Use for development at home through Xmanager
	 */
	public Connection createLocalConnection() throws SQLException {
		return DriverManager.getConnection(LOCAL_URL, CRDS.getUsername(), CRDS.getPassword());
	}

	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		DatabaseConnection dc = new DatabaseConnection();

		try (Connection con = dc.createLocalConnection()) {
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * from Branch");
			System.out.println(results);
		}
	}

}
