package com.niit.UserList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.niit.Management.EmployeeManagement;
import com.niit.Management.InventoryManagement;
import com.niit.Management.SalesData;
import com.niit.Entity.Employee_Info;
import com.niit.Entity.Inventory_Info;
import com.niit.Entity.Sales_Info;

import java.awt.event.WindowEvent;
import java.util.List;

public class Manager extends JFrame implements ActionListener {

	private Login login;
	private JMenuBar jMenuBar;
	private JMenu EmpJMenu;
	private JMenu InvJMenu;
	private JMenu SalJMenu;

	private JMenuItem AddEmp;
	private JMenuItem UpdateEmpR;
	private JMenuItem deleteEmp;
	private JMenuItem SearchEmp;
	private JMenuItem updateEmpP;

	private JMenuItem CheckQ;
	private JMenuItem CheckI;
	private JMenuItem UpdatePrice;
	private JMenuItem DeleteCommodity;
	private JMenuItem addCommodity;

	private JMenuItem CheckSalesEmp;
	private JMenuItem checkSalesMon;

	public Manager(Login log) {
		this.login = log; // 引用Login窗体对象
		this.setBounds(480, 160, 400, 330);
		this.setTitle("Manager Menu");
		jMenuBar = new JMenuBar();
		EmpJMenu = new JMenu("Employee Management");
		AddEmp = new JMenuItem("Add Details Of New Employee");
		AddEmp.addActionListener(this);
		UpdateEmpR = new JMenuItem("Update Employee Records");
		UpdateEmpR.addActionListener(this);
		deleteEmp = new JMenuItem("Delete Employee Records");
		deleteEmp.addActionListener(this);
		SearchEmp = new JMenuItem("Search Employee Records");
		SearchEmp.addActionListener(this);
		updateEmpP = new JMenuItem("Update Employee Password");
		updateEmpP.addActionListener(this);
		EmpJMenu.add(AddEmp);
		EmpJMenu.add(UpdateEmpR);
		EmpJMenu.add(deleteEmp);
		EmpJMenu.add(SearchEmp);
		EmpJMenu.add(updateEmpP);

		InvJMenu = new JMenu("Inventory Management");
		CheckQ = new JMenuItem("Check The Quantity Of The Item");
		CheckQ.addActionListener(this);
		CheckI = new JMenuItem("Check The Information Of The Item");
		CheckI.addActionListener(this);
		UpdatePrice = new JMenuItem("Update The Price Of Item");
		UpdatePrice.addActionListener(this);
		DeleteCommodity = new JMenuItem("Delete The Item");
		DeleteCommodity.addActionListener(this);
		addCommodity = new JMenuItem("Add The Item");
		addCommodity.addActionListener(this);
		InvJMenu.add(CheckQ);
		InvJMenu.add(CheckI);
		InvJMenu.add(UpdatePrice);
		InvJMenu.add(DeleteCommodity);
		InvJMenu.add(addCommodity);

		SalJMenu = new JMenu("Sales Data");
		CheckSalesEmp = new JMenuItem("Check Total Sales Based On Employee");
		checkSalesMon = new JMenuItem("Check Total Sales Based On Month");
		CheckSalesEmp.addActionListener(this);
		checkSalesMon.addActionListener(this);
		SalJMenu.add(CheckSalesEmp);
		SalJMenu.add(checkSalesMon);

		jMenuBar.add(EmpJMenu);
		jMenuBar.add(InvJMenu);
		jMenuBar.add(SalJMenu);
		JLabel jLabel = new JLabel("The AL-Nayesh Mart", JLabel.CENTER);
		this.getContentPane().add(jMenuBar, BorderLayout.NORTH);
		this.getContentPane().add(jLabel, BorderLayout.CENTER);
		this.getContentPane().setBackground(Color.green);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Manager.this.setVisible(false);
			}
		});
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == AddEmp) {
			final JFrame addFrame = new JFrame();
			addFrame.setTitle("Add Details Of New Employee");
			addFrame.setBounds(450, 200, 400, 250);
			addFrame.getContentPane().setLayout(new GridLayout(7, 1));
			JPanel jPanel = new JPanel();
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(new JLabel("Employee ID               "));
			jPanel.add(jTextField1);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField2 = new JTextField(10);
			jPanel.add(new JLabel("Employee Name          "));
			jPanel.add(jTextField2);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField3 = new JTextField(10);
			jPanel.add(new JLabel("Employee Age             "));
			jPanel.add(jTextField3);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField4 = new JTextField(10);
			jPanel.add(new JLabel("Employee Fhone        "));
			jPanel.add(jTextField4);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField5 = new JTextField(10);
			jPanel.add(new JLabel("Employee Password  "));
			jPanel.add(jTextField5);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField6 = new JTextField(10);
			jPanel.add(new JLabel("Employee Permission"));
			jPanel.add(jTextField6);
			addFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton addButton = new JButton("Add");
			addButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jTextField3.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"The Employee_Age is not allow null!",
								"Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						EmployeeManagement employeeManagement = new EmployeeManagement();
						String id = jTextField1.getText();
						String name = jTextField2.getText();
						int age = Integer.parseInt(jTextField3.getText());
						String fhone = jTextField4.getText();
						String key = jTextField5.getText();
						String permission = jTextField6.getText();
						int result;
						result = employeeManagement.Add(id, name, age, fhone,
								key, permission);
						if (result > 0) {
							JOptionPane.showMessageDialog(null,
									"The Employee is add!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			JButton quiButton = new JButton("Quit");
			quiButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					addFrame.setVisible(false);
				}
			});
			jPanel.add(addButton);
			jPanel.add(quiButton);
			addFrame.getContentPane().add(jPanel);
			addFrame.setResizable(false);
			addFrame.setVisible(true);
		} else if (a.getSource() == UpdateEmpR) {
			final JFrame updateJFrame = new JFrame();
			updateJFrame.setTitle("Update Employee Records");
			updateJFrame.setBounds(450, 200, 400, 250);
			updateJFrame.getContentPane().setLayout(new GridLayout(7, 1));
			JPanel jPanel = new JPanel();
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(new JLabel("Employee ID               "));
			jPanel.add(jTextField1);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField2 = new JTextField(10);
			jPanel.add(new JLabel("Employee Name          "));
			jPanel.add(jTextField2);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField3 = new JTextField(10);
			jPanel.add(new JLabel("Employee Age             "));
			jPanel.add(jTextField3);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField4 = new JTextField(10);
			jPanel.add(new JLabel("Employee Fhone        "));
			jPanel.add(jTextField4);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField5 = new JTextField(10);
			jPanel.add(new JLabel("Employee Password  "));
			jPanel.add(jTextField5);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField6 = new JTextField(10);
			jPanel.add(new JLabel("Employee Permission"));
			jPanel.add(jTextField6);
			updateJFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton updateButton = new JButton("Update");
			updateButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jTextField3.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"The Employee_Age is not allow null!",
								"Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						EmployeeManagement employeeManagement = new EmployeeManagement();
						String id = jTextField1.getText();
						String name = jTextField2.getText();
						int age = Integer.parseInt(jTextField3.getText());
						String fhone = jTextField4.getText();
						String key = jTextField5.getText();
						String permission = jTextField6.getText();
						int result;
						result = employeeManagement.Update(id, name, age,
								fhone, key, permission);
						if (result > 0) {
							JOptionPane.showMessageDialog(null,
									"The Employee is update!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"The Employee is not exist!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			JButton quitButton = new JButton("Quit");
			quitButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					updateJFrame.setVisible(false);
				}
			});
			jPanel.add(updateButton);
			jPanel.add(quitButton);
			updateJFrame.getContentPane().add(jPanel);
			updateJFrame.setResizable(false);
			updateJFrame.setVisible(true);
		} else if (a.getSource() == deleteEmp) {
			final JFrame delFrame = new JFrame();
			delFrame.getContentPane().setLayout(new GridLayout(2, 1));
			delFrame.setBounds(450, 200, 300, 150);
			delFrame.setTitle("Delete Employee Records");
			JPanel jPanel = new JPanel();
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(new JLabel("Employee ID"));
			jPanel.add(jTextField1);
			delFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton delButton = new JButton("Delete");
			delButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					EmployeeManagement employeeManagement = new EmployeeManagement();
					int result;
					String id = jTextField1.getText();
					result = employeeManagement.Delete(id);
					if (result > 0) {
						JOptionPane.showMessageDialog(null,
								"The Employee is delete", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			JButton quitButton = new JButton("Quit");
			quitButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					delFrame.setVisible(false);
				}
			});
			jPanel.add(delButton);
			jPanel.add(quitButton);
			delFrame.getContentPane().add(jPanel);
			delFrame.setResizable(false);
			delFrame.setVisible(true);
		} else if (a.getSource() == SearchEmp) {
			final JFrame searchjframe = new JFrame();
			JScrollPane jScrollPane = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea
					.setText("Employee_ID  Employee_Name  Employee_Age  Employee_Fhone  Employee_Password  Employee_Permission\n");
			EmployeeManagement employeeManagement = new EmployeeManagement();
			List<Employee_Info> employeeList = employeeManagement.SearchEmp();

			for (int i = 0; i < employeeList.size(); i++) {
				jTextArea.append(employeeList.get(i).getEmployee_ID() + "\t"
						+ employeeList.get(i).getEmployee_Name() + "\t  "
						+ employeeList.get(i).getEmployee_Age() + "\t  "
						+ employeeList.get(i).getEmployee_Fhone() + "\t       "
						+ employeeList.get(i).getEmployee_Password()
						+ "\t                     "
						+ employeeList.get(i).getEmployee_Permission() + "\n");
			}

			jScrollPane.add(jTextArea);
			jScrollPane.setViewportView(jTextArea);
			searchjframe.setTitle("Search Employee Records");
			searchjframe.setBounds(420, 200, 650, 250);
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
		} else if (a.getSource() == updateEmpP) {
			final JFrame uPjFrame = new JFrame();
			uPjFrame.setBounds(450, 200, 350, 200);
			uPjFrame.setTitle("Update Employee Password");
			uPjFrame.getContentPane().setLayout(new GridLayout(3, 1));
			JPanel jPanel = new JPanel();
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(new JLabel("Employee ID    "));
			jPanel.add(jTextField1);
			uPjFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			final JTextField jTextField2 = new JTextField(10);
			jPanel.add(new JLabel("New Password"));
			jPanel.add(jTextField2);
			uPjFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton uButton = new JButton("Update");
			uButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id = jTextField1.getText();
					String key = jTextField2.getText();
					int result;
					EmployeeManagement employeeManagement = new EmployeeManagement();
					result = employeeManagement.UpdatePassword(key, id);
					if (result > 0) {
						JOptionPane.showMessageDialog(null,
								"The Password is update!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"The Employee is not exist!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			JButton quiButton = new JButton("Quit");
			quiButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					uPjFrame.setVisible(false);
				}
			});
			jPanel.add(uButton);
			jPanel.add(quiButton);
			uPjFrame.getContentPane().add(jPanel);
			uPjFrame.setResizable(false);
			uPjFrame.setVisible(true);
		} else if (a.getSource() == CheckQ) {
			final JFrame CheckQjFrame = new JFrame();
			CheckQjFrame.setBounds(450, 200, 350, 150);
			CheckQjFrame.setTitle("Check The Quantity Of The Item");
			CheckQjFrame.getContentPane().setLayout(new GridLayout(2, 1));
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Item_ID"));
			final JTextField jTextField = new JTextField(10);
			jPanel.add(jTextField);
			CheckQjFrame.getContentPane().add(jPanel);
			jPanel = new JPanel();
			JButton check = new JButton("Check");
			check.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					InventoryManagement inventoryManagement = new InventoryManagement();
					String id = jTextField.getText();
					inventoryManagement.CheckTotal(id);
				}
			});
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CheckQjFrame.setVisible(false);
				}
			});
			jPanel.add(check);
			jPanel.add(quit);
			CheckQjFrame.getContentPane().add(jPanel);
			CheckQjFrame.setResizable(false);
			CheckQjFrame.setVisible(true);
		} else if (a.getSource() == CheckI) {
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
					jTextArea
							.setText("Item_ID          Item_Name          Item_Price          Item_Quantity\n");
					String id = iDjJTextField.getText();
					InventoryManagement inventoryManagement = new InventoryManagement();
					List<Inventory_Info> inventoryList = inventoryManagement
							.SearchItemForID(id);
					for (int i = 0; i < inventoryList.size(); i++) {
						jTextArea.append(inventoryList.get(i).getItem_ID()
								+ "\t" + inventoryList.get(i).getItem_Name()
								+ "\t" + inventoryList.get(i).getItem_Price()
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
		} else if (a.getSource() == UpdatePrice) {
			final JFrame upPriceFrame = new JFrame();
			upPriceFrame.setBounds(450, 200, 350, 200);
			upPriceFrame.setTitle("Update The Price Of Item");
			upPriceFrame.getContentPane().setLayout(new GridLayout(3, 1));
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Inventory Name"));
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(jTextField1);
			upPriceFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			jPanel.add(new JLabel("     New Price     "));
			final JTextField jTextField2 = new JTextField(10);
			jPanel.add(jTextField2);
			upPriceFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton uButton = new JButton("Update");
			uButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					InventoryManagement inventoryManagement = new InventoryManagement();
					String name = jTextField1.getText();
					int price = Integer.parseInt(jTextField2.getText());
					int result = inventoryManagement.UpdatePrice(name, price);
					if (result > 0) {
						JOptionPane.showMessageDialog(null,
								"The Price is update!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"The item is not exist!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			JButton qButton = new JButton("Quit");
			qButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					upPriceFrame.setVisible(false);
				}
			});
			jPanel.add(uButton);
			jPanel.add(qButton);
			upPriceFrame.getContentPane().add(jPanel);

			upPriceFrame.setResizable(false);
			upPriceFrame.setVisible(true);
		} else if (a.getSource() == DeleteCommodity) {
			final JFrame delFrame = new JFrame();
			delFrame.getContentPane().setLayout(new GridLayout(2, 1));
			delFrame.setBounds(450, 200, 300, 150);
			delFrame.setTitle("Delete The Item");
			JPanel jPanel = new JPanel();
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(new JLabel("Inventory Name"));
			jPanel.add(jTextField1);
			delFrame.getContentPane().add(jPanel);

			jPanel = new JPanel();
			JButton delButton = new JButton("Delete");
			delButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					InventoryManagement inventoryManagement = new InventoryManagement();
					int result;
					String name = jTextField1.getText();
					result = inventoryManagement.DeleteItem(name);
					if (result > 0) {
						JOptionPane.showMessageDialog(null,
								"The Item is delete", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			JButton quitButton = new JButton("Quit");
			quitButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					delFrame.setVisible(false);
				}
			});
			jPanel.add(delButton);
			jPanel.add(quitButton);
			delFrame.getContentPane().add(jPanel);
			delFrame.setResizable(false);
			delFrame.setVisible(true);
		} else if (a.getSource() == addCommodity) {
			final JFrame addComjFrame = new JFrame();
			addComjFrame.setTitle("Add The Item");
			addComjFrame.setBounds(450, 200, 350, 200);
			addComjFrame.getContentPane().setLayout(new GridLayout(5, 1));
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("   Item ID       "));
			final JTextField jTextField1 = new JTextField(10);
			jPanel.add(jTextField1);
			addComjFrame.getContentPane().add(jPanel);
			jPanel = new JPanel();
			jPanel.add(new JLabel("Item Name    "));
			final JTextField jTextField2 = new JTextField(10);
			jPanel.add(jTextField2);
			addComjFrame.getContentPane().add(jPanel);
			jPanel = new JPanel();
			jPanel.add(new JLabel("Item Price     "));
			final JTextField jTextField3 = new JTextField(10);
			jPanel.add(jTextField3);
			addComjFrame.getContentPane().add(jPanel);
			jPanel = new JPanel();
			jPanel.add(new JLabel("Item Quantity"));
			final JTextField jTextField4 = new JTextField(10);
			jPanel.add(jTextField4);
			addComjFrame.getContentPane().add(jPanel);
			jPanel = new JPanel();
			JButton add = new JButton("Add");
			JButton quit = new JButton("Quit");
			jPanel.add(add);
			add.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String id = jTextField1.getText();
					String name = jTextField2.getText();
					int price = Integer.parseInt(jTextField3.getText());
					int total = Integer.parseInt(jTextField4.getText());
					InventoryManagement inventoryManagement = new InventoryManagement();
					int result = inventoryManagement.Additem(id, name, price,
							total);
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "The Item is Add!",
								"Message", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"The item is not exist!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			jPanel.add(quit);
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addComjFrame.setVisible(false);
				}
			});
			addComjFrame.getContentPane().add(jPanel);
			addComjFrame.setResizable(false);
			addComjFrame.setVisible(true);
		} else if (a.getSource() == CheckSalesEmp) {
			final JFrame CheckSalesEmp = new JFrame();
			CheckSalesEmp.setBounds(450, 200, 400, 150);
			CheckSalesEmp.setTitle("Check Total Sales Based On Employee");
			CheckSalesEmp.getContentPane().setLayout(new GridLayout(2, 1));
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Employee_ID"));
			final JTextField jTextField = new JTextField(10);
			jPanel.add(jTextField);
			CheckSalesEmp.getContentPane().add(jPanel);
			jPanel = new JPanel();
			JButton check = new JButton("Check");
			check.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SalesData salesData = new SalesData();
					String id = jTextField.getText();
					salesData.SelectSalesForEmployee(id);
				}
			});
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CheckSalesEmp.setVisible(false);
				}
			});
			jPanel.add(check);
			jPanel.add(quit);
			CheckSalesEmp.getContentPane().add(jPanel);
			CheckSalesEmp.setResizable(false);
			CheckSalesEmp.setVisible(true);
		} else if (a.getSource() == checkSalesMon) {
			final JFrame CheckSalesEmp = new JFrame();
			CheckSalesEmp.setBounds(450, 200, 400, 150);
			CheckSalesEmp.setTitle("Check Total Sales Based On Month");
			CheckSalesEmp.getContentPane().setLayout(new GridLayout(2, 1));
			JPanel jPanel = new JPanel();
			jPanel.add(new JLabel("Month"));
			final JTextField jTextField = new JTextField(10);
			jPanel.add(jTextField);
			CheckSalesEmp.getContentPane().add(jPanel);
			jPanel = new JPanel();
			JButton check = new JButton("Check");
			check.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SalesData salesData = new SalesData();
					String date = jTextField.getText();
					salesData.SelectSalesForMonth(date);
				}
			});
			JButton quit = new JButton("Quit");
			quit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CheckSalesEmp.setVisible(false);
				}
			});
			jPanel.add(check);
			jPanel.add(quit);
			CheckSalesEmp.getContentPane().add(jPanel);
			CheckSalesEmp.setResizable(false);
			CheckSalesEmp.setVisible(true);
		}

	}
}
