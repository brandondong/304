package queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerInfo extends Information{
	String[] selected;
	
	public CustomerInfo(int id, String[] selected, Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		this.selected = selected;
		query = customerInfoToString(id, selected);
	}
	
	public Map<String, Object> getResult(){
		Map<String, Object> props = new HashMap<>();
		try {
			if (rs.next()) {
				for (String attribute : selected) {
					props.put(attribute, rs.getObject(attribute));
				}
			}
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		return props;
	}

	private String customerInfoToString(int id, String[] selected) {
		return String.format("SELECT %s FROM Customer WHERE CustomerID = %d", formatAttributes(selected), id);
	}
	
	private String formatAttributes(String[] attributes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < attributes.length; i++) {
			s.append(attributes[i]);
			if (i != attributes.length - 1) {
				s.append(", ");
			}
		}
		return s.toString();
	}

}
