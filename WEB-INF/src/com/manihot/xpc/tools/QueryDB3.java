package com.manihot.xpc.tools;

/*  
 *@author\u4f59\u82b3\uff0c\u521b\u5efa\u65e5\u671f2008-4-24  
 */
//package text;   
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QueryDB3 extends JFrame implements ActionListener {

	private Connection connection1;
	private Connection connection2;
	private Connection connection3;
	private Statement statement;
	private ResultSet resultSet;
	private JTable table;

	JTextField start;
	JTextField destination;

	JTextArea Result;
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JPanel jPanel3 = new JPanel();
	JButton Query1;
	JButton Query\uff12;
	JButton Query3;

	public QueryDB3() {
		super("\u4fe1\u606f\u67e5\u8be2");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// String url1="jdbc:odbc:RoadInfo";
		// String url2="jdbc:odbc:Roadguide";
		String url1 = "jdbc:mysql://localhost/xpcBranch";
		String url2 = "jdbc:mysql://localhost/xpcShared";
		String username = "dasuser";
		String password = "click88";
		try {
			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");

			connection1 = DriverManager.getConnection(url1, username, password);
			connection2 = DriverManager.getConnection(url2, username, password);
			connection3 = DriverManager.getConnection(url2, username, password);
		} catch (ClassNotFoundException e) {
			System.err.println("\u88c5\u8f7dJDBC/ODBC\u9a71\u52a8\u7a0b\u5e8f\u5931\u8d25\u3002");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.err.println(" \u65e0\u6cd5\u8fde\u63a5\u6570\u636e\u5e93");
			e.printStackTrace();
			System.exit(1);
		}
		Container container = this.getContentPane(); // \u5f97\u5230\u5bb9\u5668
		JPanel p1 = new JPanel(); // \u521d\u59cb\u5316\u4e00\u4e2a\u9762\u677f

		Query1 = new JButton(); // \u521d\u59cb\u5316\u6309\u94ae
		Query1
				.setText("\u3000\u3000\u3000\u3000\u3000\u3000    \u3000            \u67e5 \u8be2 \u6570 \u636e \u5e93 \u4e2d \u7684 \u6240 \u6709 \u8def \u51b5 \u4fe1 \u606f\u3000          \u3000   \u3000\u3000\u3000\u3000\u3000"); // \u8bbe\u7f6e\u6309\u94ae\u6587\u672c
		Query1.addActionListener(this);

		p1.add(Query1);
		container.add(p1, BorderLayout.NORTH); // \u5728\u5bb9\u5668\u4e0a\u589e\u52a0\u9762\u677f

		JPanel p2 = new JPanel();
		start = new JTextField(6); // \u521d\u59cb\u5316\u51fa\u53d1\u5730\u8f93\u5165\u57df
		destination = new JTextField(6);

		Query\uff12 = new JButton(); // \u521d\u59cb\u5316\u6309\u94ae
		Query\uff12.setText("\u67e5\u8be2\u8def\u5f84\u4fe1\u606f"); // \u8bbe\u7f6e\u6309\u94ae\u6587\u672c
		Query\uff12.addActionListener(this);
		Query3 = new JButton(); // \u521d\u59cb\u5316\u6309\u94ae
		Query3.setText(" \u8ba1\u7b97\u6700\u4f18\u8def\u5f84 "); // \u8bbe\u7f6e\u6309\u94ae\u6587\u672c
		Query3.addActionListener(this);
		p2.add(new JLabel("\u51fa\u53d1\u5730\uff1a")); // \u589e\u52a0\u804a\u5929\u9898\u6807\u7b7e
		p2.add(start); // \u589e\u52a0\u540d\u5b57\u8f93\u5165\u57df
		p2.add(new JLabel("\u76ee\u7684\u5730\uff1a"));
		p2.add(destination);
		p2.add(Query\uff12);
		p2.add(Query3);

		container.add(p2, BorderLayout.SOUTH);

		setSize(520, 300);// \u8bbe\u7f6e\u7a97\u53e3\u5c3a\u5bf8
		setVisible(true); // \u8bbe\u7f6e\u7a97\u53e3\u53ef\u89c6
	}

	public void actionPerformed(ActionEvent actionevent) {
		Object obj = actionevent.getSource();
		if (obj == Query1) {
			// String q1="SELECT * FROM RoadInfo";
			String q1 = "SELECT * FROM JobNote";

			try {

				statement = connection1.createStatement();
				resultSet = statement.executeQuery(q1);
				displayResultSet(resultSet);
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		} else if (obj == Query\uff12) {

			String q2 = "SELECT Start,Destination,Road,Status FROM Roadguide WHERE Start='"
					+ start.getText()
					+ "' AND Destination='"
					+ destination.getText() + "'";

			try {

				statement = connection2.createStatement();
				resultSet = statement.executeQuery(q2);
				displayResultSet(resultSet);

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		} else if (obj == Query3) {
			String q3 = "SELECT * FROM Roadguide WHERE Start='"
					+ start.getText() + "' AND Destination='"
					+ destination.getText() + "'ORDER BY Distance";
			try {

				statement = connection3.createStatement();
				resultSet = statement.executeQuery(q3);
				displayResultSet(resultSet);
				start.setText("");
				destination.setText("");
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		}
	}

	private void displayResultSet(ResultSet rs) throws SQLException {
		boolean moreRecords = rs.next();
		if (!moreRecords) {
			JOptionPane.showMessageDialog(this, "\u7ed3\u679c\u96c6\u4e2d\u65e0\u8bb0\u5f55");
			setTitle("\u65e0\u8bb0\u5f55\u663e\u793a");
			return;
		}
		Vector columnHeads = new Vector();
		Vector rows = new Vector();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); ++i)
				columnHeads.addElement(rsmd.getColumnName(i));

			do {
				rows.addElement(getNextRow(rs, rsmd));
			} while (rs.next());

			table = new JTable(rows, columnHeads);

			JScrollPane scroller = new JScrollPane(table);

			Container c = getContentPane();
			c.add(scroller, BorderLayout.CENTER);

			c.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd)
			throws SQLException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			currentRow.addElement(rs.getString(i));
		return currentRow;
	}

	public void shutDown() {
		try {
			connection1.close();
		} catch (SQLException e) {
			System.err.println("\u65e0\u6cd5\u65ad\u5f00\u8fde\u63a5");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new QueryDB3();

	}
}
