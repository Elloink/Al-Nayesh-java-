package com.niit.Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.niit.Entity.Inventory_Info;

;

public class InventoryManagement {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 微软官方驱动类
	String username = "sa";// 数据库用户名
	String password = "sa";// 数据库密码
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Al_Nayesh_Mart";// 连接数据库的地址

	public void CheckTotal(String id) {
		Connection connection = null;
		Statement smt = null;
		ResultSet rs = null;
		boolean bool;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT Item_Quantity from Inventory where Item_ID = '"
					+ id + "'";
			smt = connection.createStatement();
			rs = smt.executeQuery(sql);
			bool = rs.next();
			if (bool == false) {
				JOptionPane.showMessageDialog(null, "The Item is not exist",
						"Message", JOptionPane.WARNING_MESSAGE);
			} else {
				int quantity = rs.getInt(1);
				JOptionPane.showMessageDialog(null, "The Item_Quantity is "
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
	
	public List<Inventory_Info> SearchItem() {
		Connection connection;
		PreparedStatement preparedStatement;
		String sql = "SELECT * FROM Inventory";
		List<Inventory_Info> inventoryList = new ArrayList<Inventory_Info>();

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Inventory_Info inventory = new Inventory_Info();
				inventory.setItem_ID(resultSet.getString("Item_ID"));
				inventory.setItem_Name(resultSet.getString("Item_Name"));
				inventory.setItem_Price(resultSet.getInt("Item_Price"));
				inventory.setItem_Quantity(resultSet.getInt("Item_Quantity"));
				inventoryList.add(inventory);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryList;
	}

	public List<Inventory_Info> SearchItemForID(String id) {
		Connection connection;
		PreparedStatement preparedStatement;
		String sql = "SELECT * FROM Inventory WHERE Item_ID = ?";
		List<Inventory_Info> inventoryList = new ArrayList<Inventory_Info>();

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Inventory_Info inventory = new Inventory_Info();
				inventory.setItem_ID(resultSet.getString("Item_ID"));
				inventory.setItem_Name(resultSet.getString("Item_Name"));
				inventory.setItem_Price(resultSet.getInt("Item_Price"));
				inventory.setItem_Quantity(resultSet.getInt("Item_Quantity"));
				inventoryList.add(inventory);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryList;
	}

	public int UpdatePrice(String name, int price) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Item Name is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,
						password);
				String sql = "UPDATE Inventory SET Item_Price = ? WHERE Item_Name = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, price);
				preparedStatement.setString(2, name);
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

	public int DeleteItem(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Inventory_Name is not allow null", "Message",
						JOptionPane.WARNING_MESSAGE);
			} else {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username,
						password); // 创建连接
				String sql = "DELETE Inventory WHERE Item_Name = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
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

	public int Additem(String id, String name, int price, int total) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO Inventory values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, price);
			preparedStatement.setInt(4, total);
			result = preparedStatement.executeUpdate();
			connection.close();
			preparedStatement.close();
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
