package com.virtusa.buyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.buyit.dto.Customer;
import com.virtusa.buyit.exception.BuyitException;
import com.virtusa.buyit.util.DBUtility;



public class CustomerDAO {
	
	@Autowired
	DBUtility ds;


	public Customer insert(Customer customer) throws BuyitException
	{
		Connection connection = ds.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status =0;

		try {
			ps = connection.prepareStatement(
					"insert into customer_t(customer_name,customer_address,city,state,postal_code,user_name,password,email,mobile_number,gender)values(?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getCity());
			ps.setString(4, customer.getState());
			ps.setInt(5, customer.getPostalcode());
			ps.setString(6, customer.getUserName());
			ps.setString(7, customer.getPassword());
			ps.setString(8, customer.getEmail());
			ps.setLong(9, customer.getMobileNumber());
			ps.setString(10, customer.getGender());
			
			 status = ps.executeUpdate();
			if (status != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					customer.setId(rs.getInt(1));
				}
				
			} 

		} catch (SQLException e) {
			
			throw new BuyitException(e.toString());

		} finally {
			ds.close(rs,ps);

		}
		return customer;
	}

	public Customer get(String id) throws BuyitException {
		Customer customer = null;
		Connection connection = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement("select * from customer_t where customer_id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setAddress(rs.getString(3));
				customer.setCity(rs.getString(4));
				customer.setState(rs.getString(5));
				customer.setPostalcode(rs.getInt(6));
				customer.setUserName(rs.getString(7));
				customer.setPassword(rs.getString(8));
				customer.setEmail(rs.getString(9));
				customer.setMobileNumber(rs.getLong(10));
				customer.setGender(rs.getString(11));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BuyitException(e.toString());

		} finally {
			ds.close(rs,ps);
		}
		return customer;
	}


}
