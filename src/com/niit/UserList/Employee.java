package com.niit.UserList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.niit.Entity.Employee_Info;
import com.niit.Entity.Inventory_Info;
import com.niit.Entity.Sales_Info;
import com.niit.Management.EmployeeManagement;
import com.niit.Management.InventoryManagement;
import com.niit.Management.SalesData;

public class Employee extends JFrame implements ActionListener {

	private Login login;

	private JMenuBar jMenuBar;
	private JMenu ItemJMenu;
	private JMenu EmpJMenu;
	private JMenu SalesJMenu;
	private JMenu MesJMenu;

	private JMenuItem CheckItem;
	private JMenuItem CheckItemForNum;
	private JMenuItem CheckEmp;
	private JMenuItem CheckOwn;
	private JMenuItem CheckSales;

	public Employee(Login log) {
		this.login = log;
		this.setTitle("Employee Menu");
		this.setBounds(480, 160, 400, 330);
		jMenuBar = new JMenuBar();
		ItemJMenu = new JMenu("Item");
		CheckItem = new JMenuItem("Check details of items");
		CheckItem.addActionListener(this);
		CheckItemForNum = new JMenuItem("Check Item_Name and Item_Pice");
		CheckItemForNum.addActionListener(this);
		ItemJMenu.add(CheckItem);
		ItemJMenu.add(CheckItemForNum);

		EmpJMenu = new JMenu("Employee");
		CheckEmp = new JMenuItem("Check employee's message");
		CheckEmp.addActionListener(this);
		EmpJMenu.add(CheckEmp);

		SalesJMenu = new JMenu("Sales");
		CheckSales = new JMenuItem("Check Employee's sale message");
		CheckOwn = new JMenuItem("Check own sale message");
		CheckSales.addActionListener(this);
		CheckOwn.addActionListener(this);
		SalesJMenu.add(CheckSales);
		SalesJMenu.add(CheckOwn);

		jMenuBar.add(ItemJMenu);
		jMenuBar.add(EmpJMenu);
		jMenuBar.add(SalesJMenu);
		JLabel jLabel = new JLabel("The AL-Nayesh Mart", JLabel.CENTER);
		this.getContentPane().add(jMenuBar, BorderLayout.NORTH);
		this.getContentPane().add(jLabel, BorderLayout.CENTER);
		this.getContentPane().setBackground(Color.cyan);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Employee.this.setVisible(false);
			}
		});
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == CheckItem) {
			final JFrame CheckItemJFrame = new JFrame();
			CheckItemJFrame.setTitle("Check details of items");
			CheckItemJFrame.setBounds(450, 200, 400, 250);
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea
					.setText("Item_ID          Item_Name          Item_Price          Item_Quantity\n");
			InventoryManagement inventoryManagement = new InventoryManagement();
			List<Inventory_Info> inventoryList = inventoryManagement
					.SearchItem();
			for (int i = 0; i < inventoryList.size(); i++) {
				jTextArea.append(inventoryList.get(i).getItem_ID() + "\t"
						+ inventoryList.get(i).getItem_Name() + "\t"
						+ inventoryList.get(i).getItem_Price() + "\t"
						+ inventoryList.get(i).getItem_Quantity() + "\n");
			}
			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			CheckItemJFrame.getContentPane().add(jScrollPane);
			JPanel jPanel = new JPanel();
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					CheckItemJFrame.setVisible(false);
				}
			});
			jPanel.add(quit);
			CheckItemJFrame.getContentPane().add(jPanel, BorderLayout.SOUTH);
			CheckItemJFrame.setResizable(false);
			CheckItemJFrame.setVisible(true);
		} else if (a.getSource() == CheckItemForNum) {
			final JFrame checkIjFrame = new JFrame();
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			final JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			checkIjFrame.setTitle("Check The Information Of The Item");
			checkIjFrame.setBounds(450, 200, 400, 250);
			checkIjFrame.getContentPane().add(jScrollPane);
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Item ID"));
			final JTextField iDjJTextField = new JTextField(10);
			jPanel.add(iDjJTextField);
			JButton Check = new JButton("Check");
			Check.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jTextArea.setText("Item_Price          Item_Quantity\n");
					String id = iDjJTextField.getText();
					InventoryManagement inventoryManagement = new InventoryManagement();
					List<Inventory_Info> inventoryList = inventoryManagement
							.SearchItemForID(id);
					for (int i = 0; i < inventoryList.size(); i++) {
						jTextArea.append(inventoryList.get(i).getItem_Price()
								+ "\t"
								+ inventoryList.get(i).getItem_Quantity()
								+ "\n");
					}
				}
			});
			jPanel.add(Check);
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					checkIjFrame.setVisible(false);
				}
			});
			jPanel.add(quit);
			checkIjFrame.getContentPane().add(jPanel, BorderLayout.SOUTH);
			checkIjFrame.setResizable(false);
			checkIjFrame.setVisible(true);
		} else if (a.getSource() == CheckEmp) {
			final JFrame checkempJFrame = new JFrame();
			checkempJFrame.setTitle("Check employee's message");
			checkempJFrame.setBounds(450, 200, 450, 250);
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea
					.setText("Employee_ID     Employee_Name     Employee_Age     Employee_Fhone\n");
			EmployeeManagement employeeManagement = new EmployeeManagement();
			List<Employee_Info> employeeList = employeeManagement.SearchEmp();

			for (int i = 0; i < employeeList.size(); i++) {
				jTextArea.append(employeeList.get(i).getEmployee_ID() + "\t"
						+ employeeList.get(i).getEmployee_Name() + "\t  "
						+ employeeList.get(i).getEmployee_Age() + "\t  "
						+ employeeList.get(i).getEmployee_Fhone() + "\t       "
						+ "\n");
			}

			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			checkempJFrame.getContentPane().add(jScrollPane);
			JPanel jPanel = new JPanel();
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					checkempJFrame.setVisible(false);
				}
			});
			jPanel.add(quit);
			checkempJFrame.getContentPane().add(jPanel, BorderLayout.SOUTH);
			checkempJFrame.setResizable(false);
			checkempJFrame.setVisible(true);
		} else if (a.getSource() == CheckOwn) {

			final JFrame checkIjFrame = new JFrame();
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			final JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			checkIjFrame.setTitle("Check own sale message");
			checkIjFrame.setBounds(0, 150, 1400, 250);
			checkIjFrame.getContentPane().add(jScrollPane);
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Your ID"));
			final JTextField iDjJTextField = new JTextField(10);
			jPanel.add(iDjJTextField);
			JButton Check = new JButton("Check");
			Check.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jTextArea
							.setText("Employee_ID  Employee_Name    Today\tJanuary\tFebruary\tMarch\tApril\tMay\t"
									+ "June\tJuly\tAugust\tSeptemer\tOctober\tNovember\tDecember\tTotal\n");
					SalesData salesData = new SalesData();
					String id = iDjJTextField.getText();
					List<Sales_Info> salesList = salesData.SelectSalesForOwn(id);
					for (int i = 0; i < salesList.size(); i++) {
						jTextArea.append(salesList.get(i).getEmployee_ID()
								+ "\t" + salesList.get(i).getEmployee_Name()
								+ "\t  " + salesList.get(i).getToday() + "\t"
								+ salesList.get(i).getJanuary() + "\t"
								+ salesList.get(i).getFebruary() + "\t"
								+ salesList.get(i).getMarch() + "\t"
								+ salesList.get(i).getApril() + "\t"
								+ salesList.get(i).getMay() + "\t"
								+ salesList.get(i).getJune() + "\t"
								+ salesList.get(i).getJuly() + "\t"
								+ salesList.get(i).getAugust() + "\t"
								+ salesList.get(i).getSeptemer() + "\t"
								+ salesList.get(i).getOctober() + "\t"
								+ salesList.get(i).getNovember() + "\t"
								+ salesList.get(i).getDecember() + "\t"
								+ salesList.get(i).getTotal() + "\n");
					}
				}
			});
			jPanel.add(Check);
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					checkIjFrame.setVisible(false);
				}
			});
			jPanel.add(quit);
			checkIjFrame.getContentPane().add(jPanel, BorderLayout.SOUTH);
			checkIjFrame.setResizable(false);
			checkIjFrame.setVisible(true);
		} else if (a.getSource() == CheckSales) {
			final JFrame searchjframe = new JFrame();
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea
					.setText("Employee_ID  Employee_Name    Today\tJanuary\tFebruary\tMarch\tApril\tMay\t"
							+ "June\tJuly\tAugust\tSeptemer\tOctober\tNovember\tDecember\tTotal\n");
			SalesData salesData = new SalesData();
			List<Sales_Info> salesList = salesData.SelectSales();
			for (int i = 0; i < salesList.size(); i++) {
				jTextArea.append(salesList.get(i).getEmployee_ID() + "\t"
						+ salesList.get(i).getEmployee_Name() + "\t  "
						+ salesList.get(i).getToday() + "\t"
						+ salesList.get(i).getJanuary() + "\t"
						+ salesList.get(i).getFebruary() + "\t"
						+ salesList.get(i).getMarch() + "\t"
						+ salesList.get(i).getApril() + "\t"
						+ salesList.get(i).getMay() + "\t"
						+ salesList.get(i).getJune() + "\t"
						+ salesList.get(i).getJuly() + "\t"
						+ salesList.get(i).getAugust() + "\t"
						+ salesList.get(i).getSeptemer() + "\t"
						+ salesList.get(i).getOctober() + "\t"
						+ salesList.get(i).getNovember() + "\t"
						+ salesList.get(i).getDecember() + "\t"
						+ salesList.get(i).getTotal() + "\n");
			}

			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			searchjframe.setTitle("Search Employee Records");
			searchjframe.setBounds(0, 150, 1400, 250);
			searchjframe.getContentPane().add(jScrollPane);

			JPanel jPanel = new JPanel();
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					searchjframe.setVisible(false);
				}
			});
			jPanel.add(quit);
			searchjframe.getContentPane().add(jPanel, BorderLayout.SOUTH);
			searchjframe.setResizable(false);
			searchjframe.setVisible(true);
		}
	}

}
