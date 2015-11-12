package com.niit.UserList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.niit.Management.EmployeeManagement;

public class Login extends JFrame implements ActionListener {

	private static final String Employee_Password = null;
	private JTextField empID;
	private JTextField empPassword;
	private JButton login, quit;
	private JPanel p;
	private JComboBox jComboBox;
	private Employee employee;
	private Manager manager;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 微软官方驱动类
	String username = "sa";// 数据库用户名
	String password = "sa";// 数据库密码
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Al_Nayesh_Mart";// 连接数据库的地址

	public Login() {
		this.setBounds(500, 250, 280, 200);
		this.getContentPane().setLayout(new GridLayout(5, 1)); // 网格布局，将容器划分为3行1列
		this.setTitle("Al_Nayesh_Mart_Login");

		p = new JPanel();
		p.add(new JLabel("     Al_Nayesh_Mart"));
		this.getContentPane().add(p);
		p = new JPanel();
		p.add(new JLabel("User List"));
		jComboBox = new JComboBox();
		jComboBox.addItem("Manager");
		jComboBox.addItem("Employee");
		p.add(jComboBox);
		this.getContentPane().add(p);
		p = new JPanel();
		empID = new JTextField(10);
		p.add(new JLabel("        ID        "));
		p.add(empID);
		this.getContentPane().add(p);
		p = new JPanel();
		empPassword = new JTextField(10);
		p.add(new JLabel("Password"));
		p.add(empPassword);
		this.getContentPane().add(p);
		p = new JPanel();
		login = new JButton("Login");
		quit = new JButton("Quit");
		login.addActionListener(this);
		quit.addActionListener(this);
		p.add(login);
		p.add(quit);
		this.getContentPane().add(p);
		this.setResizable(false);
		this.setVisible(true);
	}

	public Boolean Select(String id, String key) {
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE Employee_ID = '" + id + "'"
				+ "AND Employee_Password = '" + key + "'";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			boolean bool = rs.next();
			rs.close();
			smt.close();
			con.close();
			return bool;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rootPaneCheckingEnabled;
	}

	public Boolean Selectper(String id, String key) {
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE Employee_ID = '" + id + "'"
				+ "AND Employee_Password = '" + key + "'"
				+ "AND Employee_Permission = 'YES'";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			boolean bool = rs.next();
			rs.close();
			smt.close();
			con.close();
			return bool;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rootPaneCheckingEnabled;
	}

	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource() == quit)
			this.setVisible(false);
		if (a.getSource() == login) {
			String id = empID.getText();
			String key = empPassword.getText();
			if (Select(id, key) != false
					&& jComboBox.getSelectedItem().equals("Manager")) {
				manager = new Manager(this); // 传参过程
				manager.setVisible(true);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null,
						"Welcome to AL-Nayesh Mart", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (Select(id, key) == false
					&& jComboBox.getSelectedItem().equals("Manager")) {
				JOptionPane.showMessageDialog(null,
						"The ID is not exist or Password is wrong", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (Select(id, key) == false
					&& jComboBox.getSelectedItem().equals("Employee")) {
				JOptionPane.showMessageDialog(null,
						"The ID is not exist or Password is wrong", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (Select(id, key) != false && Selectper(id, key) != false
					&& jComboBox.getSelectedItem().equals("Employee")) {
				employee = new Employee(this); // 传参过程
				employee.setVisible(true);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null,
						"Welcome to AL-Nayesh Mart", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (Select(id, key) != false && Selectper(id, key) == false
					&& jComboBox.getSelectedItem().equals("Employee")) {
				JOptionPane.showMessageDialog(null,
						"You don't have the permission", "Message",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}