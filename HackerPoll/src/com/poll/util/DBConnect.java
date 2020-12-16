package com.poll.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
		return connection;
	}
}
