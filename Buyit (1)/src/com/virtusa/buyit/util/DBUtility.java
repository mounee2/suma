package com.virtusa.buyit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.virtusa.buyit.exception.BuyitException;

public class DBUtility  {

	public static void close(Object... args) throws BuyitException  {

		for (Object object : args) {
			if (null != object) {
				try {
					if (object instanceof Connection)
						((Connection) object).close();
					else if(object instanceof PreparedStatement)
					((PreparedStatement) object).close();
					else if(object instanceof ResultSet)
						((ResultSet) object).close();

				} catch (SQLException e) {
					throw new BuyitException(e.toString());
				}
			}
		}

	}

	public static Connection getConnection() throws BuyitException{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		} catch (ClassNotFoundException e) {
			throw new BuyitException(e.toString());
		} catch (SQLException e) {
			throw new BuyitException(e.toString());
		}
		return connection;
	}
}
