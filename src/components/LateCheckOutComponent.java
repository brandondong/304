package components;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import queries.IQuery;
import ui.QueryControl;

public class LateCheckOutComponent extends AbstractQueryComponent<Object> {

	public LateCheckOutComponent(Connection con, JFrame mainFrame) {
		super(con, mainFrame);
	}

	@Override
	protected List<List<String>> parseData(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected QueryControl[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IQuery<Object> createQuery(JFormattedTextField[] textFields) {
		// TODO Auto-generated method stub
		return null;
	}

}
