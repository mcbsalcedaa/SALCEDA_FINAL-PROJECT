package com.cathleen.history;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * The main class, produces the frame and the logic behind the application
 * 
 * @author cathleen
 *
 */
public class FinancialHistory extends JFrame {
	Connection con;
	PreparedStatement prepared;
	private JPanel contentPane;
	private JTextField dateText;
	private JTextField accountText;
	private JTextField toText;
	private JTextField amountText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					FinancialHistory frame = new FinancialHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JScrollPane scrollPane;
	JPanel panel;
	JLabel label;
	Table table;

	/**
	 * Connects to the database
	 */
	@SuppressWarnings("unchecked")

	public void connect() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost/financial_history", "root", "");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Opens the financial history window
	 */
	public FinancialHistory() {
		connect();
		initialize();
		table.updateTable();
		centerTable();

	}

	/**
	 * initializes the frame
	 */
	private void initialize() {
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		table = new Table(this, con);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1289, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 85, 798, 369);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(10, 85, 300, 326);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("Transaction");

		JLabel lblNewLabel_1 = new JLabel("Date");

		JLabel lblNewLabel_2 = new JLabel("Account");

		JLabel lblNewLabel_3 = new JLabel("To");

		JLabel lblNewLabel_4 = new JLabel("Activity");
		lblNewLabel_4.setFont(new Font("Papyrus", Font.PLAIN, 12));

		JLabel lblNewLabel_5 = new JLabel("Category");

		JLabel lblNewLabel_6 = new JLabel("Amount");

		JLabel lblNewLabel_7 = new JLabel("Notes");
		lblNewLabel_1.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_2.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_3.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_4.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_5.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_6.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel_7.setFont(new Font("Papyrus", Font.PLAIN, 12));
		lblNewLabel.setFont(new Font("Papyrus", Font.PLAIN, 12));
		generateFilterCard();
		dateText = new JTextField();
		dateText.setColumns(10);

		accountText = new JTextField();
		accountText.setColumns(10);

		toText = new JTextField();
		toText.setColumns(10);

		amountText = new JTextField();
		amountText.setColumns(10);

		transactionBox = new JComboBox();

		notesText = new JTextArea();

		categoryBox = new JComboBox();

		activityBox = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(20)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(dateText, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(transactionBox, GroupLayout.PREFERRED_SIZE, 155,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(accountText, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(toText, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 46,
										GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(activityBox, GroupLayout.PREFERRED_SIZE, 155,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(notesText, GroupLayout.PREFERRED_SIZE, 157,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(categoryBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(amountText, GroupLayout.DEFAULT_SIZE, 155,
														Short.MAX_VALUE)))))
				.addGap(2)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(25)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblNewLabel_1))
								.addComponent(dateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(4).addComponent(lblNewLabel))
								.addComponent(transactionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(9)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(accountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblNewLabel_2)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblNewLabel_3))
								.addComponent(toText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(11).addComponent(lblNewLabel_4))
								.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(activityBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
								.addComponent(categoryBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(8)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblNewLabel_6))
								.addComponent(amountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(11).addComponent(lblNewLabel_7))
								.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(notesText, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
						.addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setBounds(0, 0, 1263, 494);
		contentPane.add(panel_1);

		removeButton = new JButton("Remove");

		submitButton = new JButton("Submit");

		updateButton = new JButton("Update");

		lblNewLabel_9 = new JLabel("Filter by");

		clearAllFilter = new JButton("Clear All Filter");

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.PINK);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(1128, Short.MAX_VALUE)
								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(filterBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(1115)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup().addGap(10).addComponent(
												clearAllFilter, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
						.addContainerGap())
				.addComponent(panel_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING,
						gl_panel_1.createSequentialGroup().addContainerGap()
								.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(971, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(87)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(filterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(clearAllFilter).addGap(39)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(updateButton)
								.addComponent(removeButton).addComponent(submitButton))
						.addGap(56)));
		panel_11.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("FINANCIAL MANAGER");
		lblNewLabel_8.setBounds(435, 26, 386, 44);
		panel_11.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 27));

		panel_1.setLayout(gl_panel_1);
		scrollPane.setViewportView(table.deets);
		generateComboBox();
		addAcionListeners();

		cardLayout = (CardLayout) (panel_2.getLayout());
		filterBox.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				filterBuild = new ArrayList(); // initializes everytime there's a switch in the filter pane
				try {
					if (evt.getSource() == filterBox) {
						if (filterBox.getSelectedItem().equals("Number")) {
							cardLayout.show(panel_2, "number");
						} else if (filterBox.getSelectedItem().equals("Date")) {
							cardLayout.show(panel_2, "date");
							System.out.println("in");
						} else if (filterBox.getSelectedItem().equals("Transaction")) {
							System.out.println("IN");
							cardLayout.show(panel_2, "transaction");
						} else if (filterBox.getSelectedItem().equals("Account")) {
							cardLayout.show(panel_2, "account");
						} else if (filterBox.getSelectedItem().equals("To")) {
							cardLayout.show(panel_2, "to");
						} else if (filterBox.getSelectedItem().equals("Activity")) {
							cardLayout.show(panel_2, "activity");
						} else if (filterBox.getSelectedItem().equals("Category")) {
							cardLayout.show(panel_2, "category");
						} else if (filterBox.getSelectedItem().equals("Amount")) {
							cardLayout.show(panel_2, "amount");
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
	}

	/**
	 * Generates the combobox for the details
	 */
	private void generateComboBox() {
		for (String choice : transactionChoices) {
			transactionBox.addItem(choice);
		}
		for (String choice : activityChoices) {
			activityBox.addItem(choice);
		}
		for (String choice : categoryChoices) {
			categoryBox.addItem(choice);
		}
		transactionBox.setSelectedItem(null);
		activityBox.setSelectedItem(null);
		categoryBox.setSelectedItem(null);
	}

	/**
	 * generates actionlisteners to buttons and table
	 */
	private void addAcionListeners() {
		table.deets.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				deetsMouseClicked(evt);

			}
		});
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					submitButtonActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeButtonActionPerformed(evt);
			}
		});
		updateButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateButtonActionPerformed1(evt);
			}
		});
		clearAllFilter.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onlineFilter.setSelected(false);
				physicalFilter.setSelected(false);
				depositFilter.setSelected(false);
				withdrawFilter.setSelected(false);
				investFilter.setSelected(false);
				saveFilter.setSelected(false);
				expenseFilter.setSelected(false);
				schoolFilter.setSelected(false);
				othersFilter.setSelected(false);
				transpoFilter.setSelected(false);
				groceryFilter.setSelected(false);
				shoppingFilter.setSelected(false);
				foodFilter.setSelected(false);
				filterColumn = new ArrayList();
				filterBuild = new ArrayList();
				ArrayList<String> temp = new ArrayList();
				ArrayList<String> temp2 = new ArrayList();
				ArrayList<String> temp3 = new ArrayList();
				ArrayList<String> temp4 = new ArrayList();
				ArrayList<String> temp5 = new ArrayList();
				table.updateTable();

			}
		});

	}

	/**
	 * Listens to any action, and if update button is pressed, update in the
	 * database will be executed
	 */
	private void updateButtonActionPerformed1(java.awt.event.ActionEvent evt) {
		idNumberUpdate = mouseClickRow.get(mouseClickRow.size() - 1);// finds the last id number that is clicked on the
																		// table
		getFilledDetails();

		try {
			prepared = con.prepareStatement(
					"update financialtable set date = ?, transaction = ?, account = ?, to_whom = ?, activity = ?, category = ?,  amount = ?, notes = ? where number = "
							+ idNumberUpdate);

			prepared.setString(1, date);
			prepared.setString(2, transaction);
			prepared.setString(3, account);
			prepared.setString(4, to);
			prepared.setString(5, activity);
			prepared.setString(6, category);
			prepared.setFloat(7, amount);
			prepared.setString(8, notes);

			prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.updateTable();
		centerTable();
		clearFields();
	}

	/**
	 * Checks if all the fields are filled
	 * 
	 * @return true if all fields are filled, false otherwise
	 */
	private boolean getFilledDetails() {
		try {
			date = dateText.getText();
			transaction = (String) transactionBox.getSelectedItem();
			account = accountText.getText();
			to = toText.getText();
			activity = (String) activityBox.getSelectedItem();
			category = (String) categoryBox.getSelectedItem();
			amount = Float.parseFloat(amountText.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "All fields must be filled");
			return false;
		}

		notes = notesText.getText();
		String[] fields = { transaction, account, to, activity, category, notes };
		return true;
	}

	/**
	 * Submits the record to the database
	 * 
	 * @param evt
	 * @throws SQLException
	 */
	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		if (getFilledDetails()) {
			lastIdNumber();
			prepared = con.prepareStatement(
					"insert into financialtable(number,date,transaction,account,to_whom, activity, category, amount, notes)values(?,?,?,?,?,?,?,?,?)");

			prepared.setInt(1, idNumber);
			prepared.setString(2, date);
			prepared.setString(3, transaction);
			prepared.setString(4, account);
			prepared.setString(5, to);
			prepared.setString(6, activity);
			prepared.setString(7, category);
			prepared.setFloat(8, amount);
			prepared.setString(9, notes);

			prepared.executeUpdate();

			table.updateTable();
			incrementIdNumber();
			clearFields();
			centerTable();
		}

	}

	/**
	 * removes the Data if the remove button action is clicked
	 * 
	 * @param evt
	 */
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			prepared = con.prepareStatement(
					"delete from financialtable where number = " + mouseClickRow.get(mouseClickRow.size() - 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Record Removed Successfully!");

		table.updateTable();
		clearFields();
	}

	/**
	 * Keeps track of the row that is being clicked, holds the id information
	 * 
	 * @param evt
	 */
	private void deetsMouseClicked(java.awt.event.MouseEvent evt) {
		model = (DefaultTableModel) table.deets.getModel();
		selectedRow = table.deets.getSelectedRow();
		mouseClickRow.add((Integer) model.getValueAt(selectedRow, 0));
		fillUpFields();

	}

	/**
	 * Fills up the fields depending on the row that is clicked
	 */
	private void fillUpFields() {

		dateText.setText(model.getValueAt(selectedRow, 1).toString());
		transactionBox.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
		accountText.setText(model.getValueAt(selectedRow, 3).toString());
		toText.setText(model.getValueAt(selectedRow, 4).toString());
		activityBox.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
		categoryBox.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
		amountText.setText(model.getValueAt(selectedRow, 7).toString());
		notesText.setText(model.getValueAt(selectedRow, 8).toString());

	}

	/**
	 * Updates the IdNumber to make a unique reference to every record
	 */
	private void lastIdNumber() {
		try {
			prepared = con.prepareStatement("SELECT MAX(number) FROM financialtable");
			ResultSet rs = prepared.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					idNumber = 1;
				} else {
					idNumber = rs.getInt(1);
					idNumber++;// increments by 1 to have a unique number
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Increments the number to get a unique number
	 */
	private void incrementIdNumber() {
		idNumber++;
	}

	/**
	 * Modifies the table based on what is called(Submit or Update only)
	 */
	@SuppressWarnings("unchecked")
	private void centerTable() {
		for (int x = 0; x < table.deets.getColumnCount(); x++) {
			table.deets.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
	}

	/**
	 * Clears the fields to be filled
	 */
	private void clearFields() {
		dateText.setText("");
		transactionBox.setSelectedItem(null);
		activityBox.setSelectedItem(null);
		categoryBox.setSelectedItem(null);
		categoryBox.setSelectedItem(null);
		accountText.setText("");
		notesText.setText("");
		amountText.setText("");
		toText.setText("");
	}

	/**
	 * Generates the filter card for swapping of panels for fitering
	 */
	private void generateFilterCard() {
		filterBox = new JComboBox();

		panel_2 = new JPanel();
		panel_2.setLayout(new CardLayout(0, 0));

		panel_3 = new JPanel();
		panel_3.setBackground(Color.PINK);
		panel_2.add(panel_3, "number");
		panel_3.setLayout(null);

		numberFilter = new JTextField();
		numberFilter.setBounds(31, 11, 86, 20);
		panel_3.add(numberFilter);
		numberFilter.setColumns(10);

		numberFilterButton = new JButton("Remove");
		numberFilterButton.setBounds(28, 52, 89, 23);
		panel_3.add(numberFilterButton);

		panel_4 = new JPanel();
		panel_2.add(panel_4, "date");
		panel_4.setLayout(null);

		dateFilter = new JTextField();
		dateFilter.setBounds(27, 11, 86, 20);
		panel_4.add(dateFilter);
		dateFilter.setColumns(10);

		dateFilterButton = new JButton("Remove");
		dateFilterButton.setBounds(27, 48, 89, 23);
		panel_4.add(dateFilterButton);

		panel_5 = new JPanel();
		panel_2.add(panel_5, "transaction");
		panel_5.setLayout(null);

		onlineFilter = new JCheckBox("Online");
		onlineFilter.setBounds(0, 7, 97, 23);
		panel_5.add(onlineFilter);

		physicalFilter = new JCheckBox("Physical");
		physicalFilter.setBounds(0, 33, 97, 23);
		panel_5.add(physicalFilter);

		panel_7 = new JPanel();
		panel_2.add(panel_7, "account");
		panel_7.setLayout(null);

		accountFilter = new JTextField();
		accountFilter.setBounds(25, 11, 86, 20);
		panel_7.add(accountFilter);
		accountFilter.setColumns(10);

		accountFilterButton = new JButton("Remove");
		accountFilterButton.setBounds(25, 42, 89, 23);
		panel_7.add(accountFilterButton);

		panel_8 = new JPanel();
		panel_2.add(panel_8, "To");
		panel_8.setLayout(null);

		toFilter = new JTextField();
		toFilter.setBounds(25, 11, 86, 20);
		panel_8.add(toFilter);
		toFilter.setColumns(10);

		toFilterButton = new JButton("Remove");
		toFilterButton.setBounds(25, 41, 89, 23);
		panel_8.add(toFilterButton);

		panel_6 = new JPanel();
		panel_2.add(panel_6, "activity");

		depositFilter = new JCheckBox("Deposit");

		withdrawFilter = new JCheckBox("Withdraw");

		investFilter = new JCheckBox("Invest");

		saveFilter = new JCheckBox("Save");

		expenseFilter = new JCheckBox("Expense");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6
				.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
								.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_6.createSequentialGroup().addGap(6)
												.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
														.addComponent(depositFilter).addComponent(withdrawFilter)))
										.addGroup(gl_panel_6.createSequentialGroup().addContainerGap()
												.addComponent(investFilter))
										.addGroup(gl_panel_6.createSequentialGroup().addContainerGap()
												.addComponent(saveFilter))
										.addGroup(gl_panel_6.createSequentialGroup().addContainerGap()
												.addComponent(expenseFilter)))
								.addContainerGap(35, Short.MAX_VALUE)));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup().addGap(7).addComponent(depositFilter).addGap(3)
						.addComponent(withdrawFilter).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(investFilter).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(saveFilter).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(expenseFilter).addContainerGap(168, Short.MAX_VALUE)));
		panel_6.setLayout(gl_panel_6);

		panel_9 = new JPanel();
		panel_2.add(panel_9, "category");
		schoolFilter = new JCheckBox("School");
		transpoFilter = new JCheckBox("Transportation");
		foodFilter = new JCheckBox("Food");
		groceryFilter = new JCheckBox("Grocery");
		shoppingFilter = new JCheckBox("Shopping");
		othersFilter = new JCheckBox("Others");
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING).addComponent(schoolFilter)
								.addComponent(transpoFilter).addComponent(foodFilter).addComponent(groceryFilter)
								.addComponent(shoppingFilter).addComponent(othersFilter))
						.addContainerGap(35, Short.MAX_VALUE)));
		gl_panel_9.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup().addContainerGap().addComponent(schoolFilter)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(transpoFilter)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(foodFilter)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(groceryFilter)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(shoppingFilter)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(othersFilter)
						.addContainerGap(154, Short.MAX_VALUE)));
		panel_9.setLayout(gl_panel_9);

		panel_10 = new JPanel();
		panel_2.add(panel_10, "amount");
		panel_10.setLayout(null);

		amountBox = new JComboBox();
		amountBox.setBounds(10, 23, 118, 22);
		panel_10.add(amountBox);

		amountFilter = new JTextField();
		amountFilter.setBounds(10, 62, 118, 20);
		panel_10.add(amountFilter);
		amountFilter.setColumns(10);

		amountFilterButton = new JButton("Remove");
		amountFilterButton.setBounds(25, 93, 89, 23);
		panel_10.add(amountFilterButton);
		filterBox.setSelectedItem(null);
		for (String choice : filterChoices) {
			filterBox.addItem(choice);

		}
		cardLayout = (CardLayout) (panel_2.getLayout());
		addFilterActions();
	}

	ArrayList<String> temp = new ArrayList();
	ArrayList<String> temp2 = new ArrayList();
	ArrayList<String> temp3 = new ArrayList();
	ArrayList<String> temp4 = new ArrayList();
	ArrayList<String> temp5 = new ArrayList();

	/**
	 * Adds actions(keylistener, actionlisters) to the filter objects
	 */
	private void addFilterActions() {

		numberFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					while (filterColumn.contains("number")) {
						filterColumn.remove("number");

					}
					for (String k : temp) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
					}
					filterColumn.add("number");
					filterBuild.add(numberFilter.getText());
					temp.add(numberFilter.getText());
					try {
						table.filterUpdate(filterBuild, filterColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		numberFilterButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					while (filterColumn.contains("number")) {
						filterColumn.remove("number");

					}
					for (String k : temp) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
					}
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		dateFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					while (filterColumn.contains("date")) {
						filterColumn.remove("date");
					}
					for (String k : temp2) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						try {
							table.filterUpdate(filterBuild, filterColumn);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					filterColumn.add("date");
					filterBuild.add(dateFilter.getText());
					temp2.add(dateFilter.getText());
					try {
						table.filterUpdate(filterBuild, filterColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		dateFilterButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					while (filterColumn.contains("date")) {
						filterColumn.remove("date");
					}
					for (String k : temp2) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						table.filterUpdate(filterBuild, filterColumn);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		accountFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					while (filterColumn.contains("account")) {
						filterColumn.remove("account");
					}
					for (String k : temp3) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						try {
							table.filterUpdate(filterBuild, filterColumn);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					filterColumn.add("account");
					filterBuild.add(accountFilter.getText());
					temp3.add(dateFilter.getText());
					try {
						table.filterUpdate(filterBuild, filterColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		accountFilterButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					while (filterColumn.contains("account")) {
						filterColumn.remove("account");
					}
					for (String k : temp3) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						table.filterUpdate(filterBuild, filterColumn);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		toFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					while (filterColumn.contains("to_whom")) {
						filterColumn.remove("to_whom");
					}
					for (String k : temp4) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						try {
							table.filterUpdate(filterBuild, filterColumn);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					filterColumn.add("to_whom");
					filterBuild.add(toFilter.getText());
					temp4.add(toFilter.getText());
					try {
						table.filterUpdate(filterBuild, filterColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		toFilterButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					while (filterColumn.contains("to_whom")) {
						filterColumn.remove("to_whom");
					}
					for (String k : temp4) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						table.filterUpdate(filterBuild, filterColumn);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		String temp;
		ArrayList<String> tempo = new ArrayList();
		amountFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					while (filterColumn.contains("amount")) {
						filterColumn.remove("amount");
					}
					for (String k : temp5) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
					}
					if (amountBox.getSelectedItem() == "Greater than") {
						filterColumn.add("amount");

						filterBuild.add("> " + amountFilter.getText());
						temp5.add("> " + amountFilter.getText());
					} else if (amountBox.getSelectedItem() == "Lesser than") {
						filterColumn.add("amount");

						filterBuild.add("< " + amountFilter.getText());
						temp5.add("< " + amountFilter.getText());
					} else if (amountBox.getSelectedItem() == "Equal to") {
						filterColumn.add("amount");

						filterBuild.add("= " + amountFilter.getText());
						temp5.add("= " + amountFilter.getText());
					}
					try {
						table.filterUpdate(filterBuild, filterColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		amountBox.addItem("Greater than");
		amountBox.addItem("Lesser than");
		amountBox.addItem("Equal to");
		amountBox.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == amountBox) {
					if (amountBox.getSelectedItem().equals("Greater than")) {
						tempo.add("Greater than");
					}
				} else if (amountBox.getSelectedItem().equals("Lesser than")) {
					tempo.add("Lesser than");

				} else if (amountBox.getSelectedItem().equals("Equal to")) {

					tempo.add("Equal to");
				}
			}
		});
		amountFilterButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					while (filterColumn.contains("amount")) {
						filterColumn.remove("amount");
					}
					for (String k : temp5) {
						while (filterBuild.contains(k)) {
							filterBuild.remove(k);
						}
						table.filterUpdate(filterBuild, filterColumn);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		onlineFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("transaction");
					filterBuild.add("online");

				} else {
					filterColumn.remove("transaction");
					filterBuild.remove("online");
				}
				try {

					table.filterUpdate(filterBuild, filterColumn);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		physicalFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("transaction");
					filterBuild.add("physical");
				} else {
					filterColumn.remove("transaction");
					filterBuild.remove("physical");
				}
				try {

					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		depositFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("activity");
					filterBuild.add("deposit");
				} else {
					filterColumn.remove("activity");
					filterBuild.remove("deposit");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		withdrawFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("activity");
					filterBuild.add("withdraw");
				} else {
					filterColumn.remove("activity");
					filterBuild.remove("withdraw");
				}
				try {

					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		investFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("activity");
					filterBuild.add("invest");
				} else {
					filterColumn.remove("activity");
					filterBuild.remove("invest");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		saveFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("activity");
					filterBuild.add("save");
				} else {
					filterColumn.remove("activity");
					filterBuild.remove("save");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		expenseFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("activity");
					filterBuild.add("expense");
				} else {
					filterColumn.remove("activity");
					filterBuild.remove("expense");
				}

				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		schoolFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("school");
				} else {
					filterColumn.remove("category");
					filterBuild.remove("school");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		transpoFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("transpo");
				} else {
					filterColumn.remove("category");
					filterBuild.remove("transpo");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		foodFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("food");
				} else {
					filterColumn.add("category");
					filterBuild.remove("food");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		groceryFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("grocery");
				} else {
					filterColumn.add("category");
					filterBuild.remove("grocery");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		shoppingFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("shopping");
				} else {
					filterColumn.remove("category");
					filterBuild.remove("shopping");
				}
				try {
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		othersFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					filterColumn.add("category");
					filterBuild.add("others");
				} else {
					filterBuild.remove("others");
				}
				try {
					filterColumn.remove("category");
					table.filterUpdate(filterBuild, filterColumn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	String[] filterChoices = { "Number", "Date", "Transaction", "Account", "To", "Activity", "Category", "Amount" };
	private String[] transactionChoices = { "online", "physical" };
	private String[] activityChoices = { "deposit", "withdraw", "invest", "save", "expense" };
	private String[] categoryChoices = { "school", "transpo", "food", "grocery", "shopping", "others" };
	private JComboBox transactionBox;
	private JComboBox activityBox;
	private JComboBox categoryBox;
	private JButton updateButton;
	private JButton submitButton;
	private JButton removeButton;
	// Below is getting the texts of the fields
	private String date;
	private String transaction;
	private String account;
	private String to;
	private String activity;
	private String category;
	private float amount;
	// End
	private String notes;
	private int idNumber = 1;
	private DefaultTableModel model;
	private JTextArea notesText;
	private JLabel lblNewLabel_9;
	private ArrayList<Integer> mouseClickRow = new ArrayList();; // keeps track of the row that the mouseclicked
	private int idNumberUpdate; // the id to be updated
	CardLayout cardLayout;
	JPanel panel_2;
	private JPanel panel_3;
	private JTextField numberFilter;
	private JPanel panel_4;
	private JTextField dateFilter;
	private JPanel panel_5;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_6;
	private JPanel panel_9;
	private JPanel panel_10;
	private JTextField accountFilter;
	private JTextField toFilter;
	private JCheckBox schoolFilter;
	private JCheckBox transpoFilter;
	private JCheckBox foodFilter;
	private JCheckBox groceryFilter;
	private JCheckBox shoppingFilter;
	private JCheckBox othersFilter;
	private JTextField amountFilter;
	private JComboBox filterBox;
	private int selectedRow;
	private DefaultTableCellRenderer centerRenderer;
	private ArrayList<String> filterBuild = new ArrayList(); // for checkboxes to add to mysql
	private ArrayList<String> filterColumn = new ArrayList();
	private JCheckBox physicalFilter;
	private JCheckBox onlineFilter;
	private JCheckBox depositFilter;
	private JCheckBox withdrawFilter;
	private JCheckBox investFilter;
	private JCheckBox saveFilter;
	private JCheckBox expenseFilter;
	private JButton numberFilterButton;
	private JButton dateFilterButton;
	private JButton toFilterButton;
	private JButton accountFilterButton;
	private JButton amountFilterButton;
	private JComboBox amountBox;
	private JButton clearAllFilter;
}
