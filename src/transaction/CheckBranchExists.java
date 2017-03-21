package transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class CheckBranchExists extends Information{

	public CheckBranchExists(String street, String houseNo, String postalCode, Connection con) {
		super(con);
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
		}
		query = checkBranchExistsToString(street, houseNo, postalCode);
	}
	
	public boolean getResult(){
		try {
			rs.next();
			return rs.getInt("Count") == 0;
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			System.exit(-1);
			return false;
		}
	}
	
	private String checkBranchExistsToString(String street, String houseNo, String postalCode) {
		return String.format(
				"SELECT COUNT(*) AS COUNT FROM Branch "
						+ "WHERE Street = '%s' AND HouseNumber = '%s' AND PostalCode = '%s'",
				street, houseNo, postalCode);
	}

}
