package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractQuery implements IQuery {

	private final Connection con;
	Statement stmt;
	ResultSet rs;
	String query;

	public AbstractQuery(Connection con) {
		this.con = con;
	}

	@Override
	public void execute() throws SQLException {
		rs = stmt.executeQuery(query);
	}

	@Override
	public void close() {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
	}
}
