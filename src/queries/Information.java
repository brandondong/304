package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Information implements InformationInterface{
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;
	ResultSetMetaData rsmd;
	
	public Information(Connection con){
		this.con = con;
	}

	public void execute(){
		try {
			rs = stmt.executeQuery(query);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			System.out.println("ERROR");
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
	}
	
	public int numColumns(){
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
			return -1;
		}
	}
	
	public String getColumnName(int index){
		try {
			return rsmd.getColumnName(index+1);
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
			return null;
		}
	}
	
	public void close(){
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
	}
}
