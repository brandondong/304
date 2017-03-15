package web;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import oracle.jdbc.driver.OracleDriver;

@SpringBootApplication
class Application {

	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		SpringApplication.run(Application.class, args);
	}

}
