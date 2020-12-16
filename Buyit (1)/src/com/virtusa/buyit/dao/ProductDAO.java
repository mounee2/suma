package com.virtusa.buyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.virtusa.buyit.dto.Product;
import com.virtusa.buyit.exception.BuyitException;
import com.virtusa.buyit.util.DBUtility;


public class ProductDAO {

	public Product insert(Product product) throws BuyitException {
		Connection connection = DBUtility.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"insert into product_t(product_description,product_finish,standard_price)values(?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getDescription());
			ps.setString(2, product.getFinish());
			ps.setDouble(3, product.getStandardPrice());

			int status = ps.executeUpdate();
			if (status != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					product.setProductId(rs.getInt(1));
				}

			}
		} catch (SQLException e) {
			throw new BuyitException(e.toString());
		} finally {
			DBUtility.close(rs, ps, connection);
		}

		return product;

	}

	public static ArrayList<Product> get() throws BuyitException

	{

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		ArrayList<Product> productList = null;
		try {
			connection = DBUtility.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from product_t ");
			if (rs.next()) {
				productList = new ArrayList<Product>();
				do {
					Product product = new Product();
					product.setProductId(rs.getInt(1));
					product.setDescription(rs.getString(2));

					product.setFinish(rs.getString(3));
					product.setStandardPrice(rs.getDouble(4));
					productList.add(product);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtility.close(rs, statement, connection);
		}
		return productList;

	}
	public static Product get(int productId) throws BuyitException

	{
		Product product = null;
		Connection connection = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement("select * from product_t where product_id=?");
			ps.setInt(1,productId);
			rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt(1));
				product. setDescription(rs.getString(2));
				product.setFinish(rs.getString(3));
				product.setStandardPrice(rs.getDouble(4));
				

			}
			 //OrderDAO.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BuyitException(e.getMessage());
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

				if (null != connection)
					connection.close();

			} catch (SQLException e) {
				throw new BuyitException(e.getMessage());
			}
		}
		return product;
	}
}
