package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for holding the connection to the Ugrad database
 */
public class DatabaseConnection implements AutoCloseable {

	/**
	 * Set to <code>true</code> for development at home through Xmanager
	 */
	private static final boolean USE_LOCAL = true;

	private static final String URL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

	private static final String LOCAL_URL = "jdbc:oracle:thin:@localhost:1522:ug";

	private static final IDatabaseCredentials CRDS = IDatabaseCredentials.get();

	private static final DatabaseConnection INSTANCE = new DatabaseConnection();

	private Connection connection;

	private DatabaseConnection() {
		String url = USE_LOCAL ? LOCAL_URL : URL;
		try {
			connection = DriverManager.getConnection(url, CRDS.getUsername(), CRDS.getPassword());
		} catch (SQLException e) {
			throw new IllegalStateException("Failed to connect to Ugrad database: " + e.getMessage());
		}
	}

	public static DatabaseConnection instance() {
		return INSTANCE;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void close() throws SQLException {
		connection.close();
	}

}
