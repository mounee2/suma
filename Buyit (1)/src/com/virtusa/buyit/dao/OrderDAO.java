package com.virtusa.buyit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.virtusa.buyit.dto.Order;
import com.virtusa.buyit.dto.Product;
import com.virtusa.buyit.exception.BuyitException;
import com.virtusa.buyit.util.DBUtility;

public class OrderDAO {

	public int insert(Order order) throws BuyitException {
		// Order order=new Order();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement("insert into order_t(order_date,customer_id)values(?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			Calendar calendar = Calendar.getInstance();
			java.sql.Date ourJavaDateObject = new java.sql.Date(order.getOrderDate().getTime());
			ps.setDate(1, ourJavaDateObject);
			ps.setInt(2, order.getCustomer().getId());
			int i = ps.executeUpdate();
			if (i != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					order.setId(rs.getInt(1));

					for (Product product : order.getOrderedProductsList()) {

						updateOrderLine(order.getId(), product.getProductId(), product.getQuantity());

					}

				}
				System.out.println("Inserted Successfully");
			} else {
				System.out.println("Not Inserted Successfully");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BuyitException(e.toString());
		}
		return order.getId();

	}

	public static boolean updateOrderLine(int orderId, int productId, int quantity) throws BuyitException {
		Connection connection = DBUtility.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection
					.prepareStatement("insert into order_line_t(order_id,product_id,order_quantity)values(?,?,?)");
			ps.setInt(1, orderId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			int i = ps.executeUpdate();
			if (i != 0) {

				System.out.println("Inserted Successfully");
			} else {
				System.out.println("not Successfully Inserted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BuyitException(e.toString());

		}

		return false;

	}

	public Order get(int id) throws BuyitException

	{
		Order order = null;
		Product product = null;
		Connection connection = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement("select * from order_line_t where order_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {

				order.setId(rs.getInt(1));
				product.setQuantity(rs.getInt(2));
				product.setProductId(rs.getInt(3));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BuyitException(e.toString());

		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

				if (null != connection)
					connection.close();

			} catch (SQLException e) {
				throw new BuyitException(e.toString());

			}
		}
		return order;
	}

}
