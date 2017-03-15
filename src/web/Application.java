package web;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import core.DatabaseConnection;
import oracle.jdbc.driver.OracleDriver;

@SpringBootApplication
class Application {

	/**
	 * Establishes a connection to the database and then starts up the server
	 */
	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());

		try (DatabaseConnection dc = DatabaseConnection.instance()) {
			SpringApplication.run(Application.class, args);
		}
	}

}
