package queries;

import java.sql.SQLException;

public interface IQuery {

	public void execute() throws SQLException;

	public void close();
}
