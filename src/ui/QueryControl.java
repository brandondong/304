package ui;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class QueryControl {

	private final String label;

	private final JFormattedTextField field;

	private QueryControl(String label) {
		this.label = label;
		field = new JFormattedTextField();
	}

	private QueryControl(String label, JFormattedTextField.AbstractFormatter formatter) {
		this.label = label;
		field = new JFormattedTextField(formatter);
	}

	public String getLabel() {
		return label;
	}

	public JFormattedTextField getField() {
		return field;
	}

	public static QueryControl text(String label) {
		return new QueryControl(label);
	}

	public static QueryControl integer(String label) {
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setValueClass(Integer.class);

		return new QueryControl(label, numberFormatter);
	}

}
