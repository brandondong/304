package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerInfoByName extends AbstractQuery<Map<String, String>> {

	private final String name;

	private final String[] selected;

	public CustomerInfoByName( String name, String[] selected) {
		this.name = name;
		this.selected = selected;
	}
	
	@Override
	protected Map<String, String> parseResult(ResultSet rs) throws SQLException {
		Map<String, String> props = new HashMap<>();
		if (rs.next()) {
			for (String attribute : selected) {
				props.put(attribute, rs.getString(attribute));
			}
		}
		return props;
	}
	
	
	
	

	@Override
	protected String getQueryDefinition() {
		return String.format("SELECT %s FROM Customer WHERE Name = '%s'", formatAttributes(selected), name);
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