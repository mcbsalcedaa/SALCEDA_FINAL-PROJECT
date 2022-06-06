package com.cathleen.history;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Generates the table and all the necessary program that will update the table
 * 
 * @author cathleen
 *
 */
public class Table {
	JFrame frame;
	Connection connection;
	PreparedStatement prepared;

	public Table(JFrame frame, Connection connection) {
		this.frame = frame;
		this.connection = connection;
		generateTable();

	}

	public void generateTable() {
		deets = new JTable();
		deets.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "No.", "Date", "Transaction", "Account", "To", "Activity", "Category", "Amount",
				"Comment" }) {
			Class[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
					java.lang.Float.class, java.lang.String.class };

			@Override
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		deets.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		deets.setGridColor(new java.awt.Color(255, 255, 255));
		deets.setShowGrid(true);
		deets.setShowHorizontalLines(true);
		deets.setShowVerticalLines(true);
		deets.setGridColor(Color.GRAY);
		deets.setShowGrid(true);

		deets.setBorder(new EtchedBorder(EtchedBorder.RAISED));

	}

	public void updateTable() {

		int c;
		try {
			prepared = connection.prepareStatement("Select * FROM financialtable");
			ResultSet rs = prepared.executeQuery();

			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) deets.getModel();
			d.setRowCount(0);
			String[] columns = { "number", "date", "transaction", "account", "to_whom", "activity", "category",
					"amount", "notes" };
			while (rs.next()) {
				Vector v2 = new Vector();
				for (int i = 1; i <= c; i++) {
					v2.add(rs.getInt("number"));
					v2.add(rs.getString("date"));
					v2.add(rs.getString("transaction"));
					v2.add(rs.getString("account"));
					v2.add(rs.getString("to_whom"));
					v2.add(rs.getString("activity"));
					v2.add(rs.getString("category"));
					v2.add(rs.getFloat("amount"));
					v2.add(rs.getString("notes"));

				}
				d.addRow(v2);
			}
		} catch (SQLException ex) {

		}

	}

	/**
	 * Used for checkboxes filter
	 */
	public void filterUpdate(ArrayList<String> query, ArrayList<String> column) throws Exception {
		ArrayList<String> query1 = removeDuplicates(query);
		ArrayList<String> column1 = removeDuplicates(column);
		String build;
		StringBuilder filterBuild = new StringBuilder();
		int length = query1.size();
		System.out.println(length);
		System.out.println(query);
		System.out.println(column);
		Map<String, Long> frequencyMap = column.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // we use a hashp map to
																								// get a key value pair
																								// for every column that
																								// is stored in the
																								// filterColumn

		if (length == 0) {
			updateTable();
		}
		if (length == 1) {
			try {
				prepared = connection.prepareStatement("Select * FROM financialtable WHERE " + column.get(length - 1)
						+ " = '" + query1.get(length - 1) + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (length == 1 && column1.get(0).equals("amount")) {
			prepared = connection.prepareStatement(
					"Select * FROM financialtable WHERE " + column.get(length - 1) + query.get(length - 1));
		} else if (length > 1) {

			int i = 0;
			int col = 0;
			if (length == 2 && column1.size() == 2) {
				filterBuild.append("(" + column1.get(col));
				filterBuild.append(" = '");
				filterBuild.append(query1.get(i));
				filterBuild.append("') AND ");
				filterBuild.append("(" + column1.get(col + 1));
				filterBuild.append(" = '");
				filterBuild.append(query1.get(i + 1));
				filterBuild.append("')");
				build = filterBuild.toString();
				System.out.println(build);

			} else if (length == 2 && column1.size() == 1) {
				filterBuild.append("(" + column1.get(col));
				filterBuild.append(" = '");
				filterBuild.append(query1.get(i));
				filterBuild.append("') OR ");
				filterBuild.append("(" + column1.get(col));
				filterBuild.append(" = '");
				filterBuild.append(query1.get(i + 1));
				filterBuild.append("')");
				build = filterBuild.toString();
				System.out.println(build);
			} else {
				for (String columns : column1) {

					if (columns.equals("amount")) {

						filterBuild.append("(" + column1.get(col));
						filterBuild.append(query1.get(i));
						filterBuild.append(")");
					} else {
						if (frequencyMap.get(columns) == 1) {
							System.out.println("in");
							filterBuild.append("(");
						}
						for (int j = 1; j < frequencyMap.get(columns); j++) {

							filterBuild.append("(" + column1.get(col));
							filterBuild.append(" = '");
							filterBuild.append(query1.get(i));
							filterBuild.append("' OR ");
							i++;

						}

						filterBuild.append(column1.get(col));
						filterBuild.append(" = '");
						filterBuild.append(query1.get(i));
						filterBuild.append("') AND ");
						col++;
						i++;
						System.out.println(columns);

					}
					String temp = filterBuild.toString();
					build = temp.substring(0, temp.length() - 5);
				}
				String temp = filterBuild.toString();
				build = temp;

				System.out.println(build);
			}

			prepared = connection.prepareStatement("Select * FROM financialtable WHERE " + build);
		}

		try

		{

			ResultSet rs = prepared.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			int c = rsd.getColumnCount();
			DefaultTableModel d = (DefaultTableModel) deets.getModel();
			d.setRowCount(0);
			String[] column1s = { "number", "date", "transaction", "account", "to_whom", "activity", "category",
					"amount", "notes" };
			while (rs.next()) {
				Vector v2 = new Vector();

				for (int k = 1; k <= c; k++) {
					v2.add(rs.getInt("number"));
					v2.add(rs.getString("date"));
					v2.add(rs.getString("transaction"));
					v2.add(rs.getString("account"));
					v2.add(rs.getString("to_whom"));
					v2.add(rs.getString("activity"));
					v2.add(rs.getString("category"));
					v2.add(rs.getFloat("amount"));
					v2.add(rs.getString("notes"));

				}
				d.addRow(v2);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function to remove duplicates from an ArrayList
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		// Create a new ArrayList
		ArrayList<T> newList = new ArrayList<T>();

		// Traverse through the first list
		for (T element : list) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		// return the new list
		return newList;
	}

	public JTable deets;
}
