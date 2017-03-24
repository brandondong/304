package components;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import queries.IQuery;
import ui.SpringUtilities;

public abstract class AbstractQueryComponent<T> implements ActionListener {

	private final Connection con;

	private final JFrame mainFrame;

	private JTextField textFields[];

	public AbstractQueryComponent(Connection con, JFrame mainFrame) {
		this.con = con;
		this.mainFrame = mainFrame;
	}

	public void render() {
		textFields = makeGrid(getLabels());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		IQuery<T> query = createQuery(textFields);
		try {
			T results = query.execute(con);
			System.out.println("Successful");
			// TODO display results
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO display failures
		}
	}

	private JTextField[] makeGrid(String[] labels) {
		int numPairs = labels.length;
		Insets insets = mainFrame.getInsets();
		mainFrame.setSize(new Dimension(insets.left + insets.right + 500, insets.top + insets.bottom + 500));

		JTextField j[] = new JTextField[numPairs];
		Container contentPane = mainFrame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		mainFrame.setVisible(true);

		JPanel p = new JPanel(new SpringLayout());

		mainFrame.setContentPane(p);

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			JTextField textField = new JTextField(10);
			j[i] = textField;
			l.setLabelFor(textField);
			p.add(textField);
		}
		JLabel l = new JLabel("  ", JLabel.TRAILING);
		p.add(l);
		JButton finish = new JButton("Finish");
		l.setLabelFor(finish);
		p.add(finish);

		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2, 6, 6, 6, 6);

		j[0].requestFocus();
		finish.addActionListener(this);
		return j;
	}

	protected abstract String[] getLabels();

	protected abstract IQuery<T> createQuery(JTextField[] textFields);

}
