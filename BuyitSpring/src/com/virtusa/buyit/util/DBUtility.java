package com.virtusa.buyit.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.virtusa.buyit.exception.BuyitException;

public class DBUtility {

	DataSource ds;
	
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public Connection getConnection() throws BuyitException {
		try {
			Connection connection =ds.getConnection(); 
			System.out.println("Conection established succesfully");
			return connection;
		} catch (SQLException e) {
			throw new BuyitException("Sql exception");
		}
	}
	
	public void close(Object... args) throws BuyitException  {

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
}
