package com.niit.Management;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.niit.Entity.Employee_Info;
import com.niit.Entity.Sales_Info;

public class SalesData {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 微软官方驱动类
	String username = "sa";// 数据库用户名
	String password = "sa";// 数据库密码
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Al_Nayesh_Mart";// 连接数据库的地址
	private Array quantity;

	public void SelectSalesForEmployee(String id) {
		Connection connection = null;
		Statement smt = null;
		ResultSet rs = null;
		boolean bool;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT Total from Sales where Employee_ID = '" + id
					+ "'";
			smt = connection.createStatement();
			rs = smt.executeQuery(sql);
			bool = rs.next();
			if (bool == false) {
				JOptionPane.showMessageDialog(null,
						"The Employee is not exist", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int quantity = rs.getInt(1);
				JOptionPane.showMessageDialog(null, "The Sales_Total is "
						+ quantity + "", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			rs.close();
			smt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SelectSalesForMonth(String date) {
		Connection connection = null;
		Statement smt = null;
		ResultSet rs = null;
		boolean bool;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT SUM("+date+") FROM Sales ";
			smt = connection.createStatement();
			rs = smt.executeQuery(sql);
			bool = rs.next();
			if (bool == false) {
				JOptionPane.showMessageDialog(null,
						"The Month is not exist", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				int quantity = rs.getInt(1);
				JOptionPane
						.showMessageDialog(null, "The " + date + "'s sales Quantity is " + quantity
								+ "", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			rs.close();
			smt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Sales_Info> SelectSales() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Sales_Info> SalesList = new ArrayList<Sales_Info>();
		String sql = "SELECT * FROM Sales";

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sales_Info sales = new Sales_Info();
				sales.setEmployee_ID(resultSet.getString("Employee_ID"));
				sales.setEmployee_Name(resultSet.getString("Employee_Name"));
				sales.setToday(resultSet.getInt("Today"));
				sales.setJanuary(resultSet.getInt("January"));
				sales.setFebruary(resultSet.getInt("February"));
				sales.setMarch(resultSet.getInt("March"));
				sales.setApril(resultSet.getInt("April"));
				sales.setMay(resultSet.getInt("May"));
				sales.setJune(resultSet.getInt("June"));
				sales.setJuly(resultSet.getInt("July"));
				sales.setAugust(resultSet.getInt("August"));
				sales.setSeptemer(resultSet.getInt("Septemer"));
				sales.setOctober(resultSet.getInt("October"));
				sales.setNovember(resultSet.getInt("November"));
				sales.setDecember(resultSet.getInt("December"));
				sales.setTotal(resultSet.getInt("Total"));
				SalesList.add(sales);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return SalesList;
	}
	
	public List<Sales_Info> SelectSalesForOwn(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Sales_Info> SalesList = new ArrayList<Sales_Info>();
		String sql = "SELECT * FROM Sales WHERE Employee_ID = '"+id+"'";

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Sales_Info sales = new Sales_Info();
				sales.setEmployee_ID(resultSet.getString("Employee_ID"));
				sales.setEmployee_Name(resultSet.getString("Employee_Name"));
				sales.setToday(resultSet.getInt("Today"));
				sales.setJanuary(resultSet.getInt("January"));
				sales.setFebruary(resultSet.getInt("February"));
				sales.setMarch(resultSet.getInt("March"));
				sales.setApril(resultSet.getInt("April"));
				sales.setMay(resultSet.getInt("May"));
				sales.setJune(resultSet.getInt("June"));
				sales.setJuly(resultSet.getInt("July"));
				sales.setAugust(resultSet.getInt("August"));
				sales.setSeptemer(resultSet.getInt("Septemer"));
				sales.setOctober(resultSet.getInt("October"));
				sales.setNovember(resultSet.getInt("November"));
				sales.setDecember(resultSet.getInt("December"));
				sales.setTotal(resultSet.getInt("Total"));
				SalesList.add(sales);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return SalesList;
	}
}
