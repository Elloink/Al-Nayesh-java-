package com.niit.Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.niit.Entity.Employee_Info;

public class EmployeeManagement {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 微软官方驱动类
	String username = "sa";// 数据库用户名
	String password = "sa";// 数据库密码
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Al_Nayesh_Mart";// 连接数据库的地址

	public int Add(String id, String name, int age, String fhone, String key,
			String permission) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (!Pattern.matches("[a-zA-Z]{1}[0-9]{3}", id)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Employee ID must begin with letter followed by any 3 digits",
								"Message", JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("[a-zA-Z]{6}", key)) {
				JOptionPane.showMessageDialog(null,
						"Password must be 6 characters long", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (id.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee ID is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee Name is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (fhone.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee Fhone is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("YES", permission)
					& !Pattern.matches("NO", permission)) {
				JOptionPane.showMessageDialog(null,
						"Employee permission is YES or NO", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver); // 加载驱动
				connection = DriverManager.getConnection(url, username,
						password); // 创建连接
				String sql = "INSERT INTO Employee values(?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql); // 创建预编译的声明
				preparedStatement.setString(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setInt(3, age);
				preparedStatement.setString(4, fhone);
				preparedStatement.setString(5, key);
				preparedStatement.setString(6, permission);
				result = preparedStatement.executeUpdate();
				connection.close();
				preparedStatement.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int Update(String id, String name, int age, String fhone,
			String key, String permission) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (!Pattern.matches("[a-zA-Z]{1}[0-9]{3}", id)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Employee ID must begin with letter followed by any 3 digits",
								"Message", JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("[a-zA-Z]{6}", key)) {
				JOptionPane.showMessageDialog(null,
						"Password must be 6 characters long", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (id.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee ID is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee Name is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (fhone.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee Fhone is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("YES", permission)
					& !Pattern.matches("NO", permission)) {
				JOptionPane.showMessageDialog(null,
						"Employee permission is YES or NO", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver); // 加载驱动
				connection = DriverManager.getConnection(url, username,
						password); // 创建连接
				String sql = "UPDATE Employee SET Employee_Name = ?, Employee_Age = ?, Employee_Fhone = ?,"
						+ " Employee_Password = ?, Employee_Permission = ? WHERE Employee_ID = ?";
				preparedStatement = connection.prepareStatement(sql); // 创建预编译的声明
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, fhone);
				preparedStatement.setString(4, key);
				preparedStatement.setString(5, permission);
				preparedStatement.setString(6, id);
				result = preparedStatement.executeUpdate();
				connection.close();
				preparedStatement.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int Delete(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (id.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee ID is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,
						password); // 创建连接
				String sql = "DELETE Employee WHERE Employee_ID = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, id);
				result = preparedStatement.executeUpdate();
				connection.close();
				preparedStatement.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Employee_Info> SearchEmp() {
		Connection connection;
		PreparedStatement preparedStatement;
		String sql = "SELECT * FROM Employee ";
		List<Employee_Info> employeeList = new ArrayList<Employee_Info>();
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee_Info employee = new Employee_Info();
				employee.setEmployee_ID(resultSet.getString("Employee_ID"));
				employee.setEmployee_Name(resultSet.getString("Employee_Name"));
				employee.setEmployee_Age(resultSet.getInt("Employee_Age"));
				employee.setEmployee_Fhone(resultSet
						.getString("Employee_Fhone"));
				employee.setEmployee_Password(resultSet
						.getString("Employee_Password"));
				employee.setEmployee_Permission(resultSet
						.getString("Employee_Permission"));
				employeeList.add(employee);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public int UpdatePassword(String key, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (key.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee Password is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("[a-zA-Z]{1}[0-9]{3}", id)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Employee ID must begin with letter followed by any 3 digits",
								"Message", JOptionPane.WARNING_MESSAGE);
			} else if (id.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee ID is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else if (!Pattern.matches("[a-zA-Z]{6}", key)) {
				JOptionPane.showMessageDialog(null,
						"Password must be 6 characters long", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,
						password);
				String sql = "UPDATE Employee SET Employee_Password = ? WHERE Employee_ID = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, key);
				preparedStatement.setString(2, id);
				result = preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
