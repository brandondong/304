package queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reservation;

public class ModifyReservation extends AbstractQuery<Reservation>{

	public ModifyReservation(String checkIn, String checkout, int confirmationID) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Reservation parseResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
