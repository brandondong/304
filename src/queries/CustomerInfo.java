package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerInfo extends AbstractQuery<Map<String, Object>> {

	private final int id;

	private final String[] selected;

	public CustomerInfo(int id, String[] selected, Connection con) {
		super(con);
		this.id = id;
		this.selected = selected;
	}

	@Override
	protected Map<String, Object> parseResult(ResultSet rs) throws SQLException {
		Map<String, Object> props = new HashMap<>();
		if (rs.next()) {
			for (String attribute : selected) {
				props.put(attribute, rs.getObject(attribute));
			}
		}
		return props;
	}

	@Override
	protected String getQueryDefinition() {
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
