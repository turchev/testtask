package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.OrderStatusType;
import com.haulmont.testtask.entity.Orders;

class OrdersDaoJdbc implements OrdersDao {
	private DataSource ds = null;
	private static final String SELECT_ALL = "select * from orders;";

	public OrdersDaoJdbc(DataSource ds) {
		this.ds = ds;
	}	

	@Override
	public synchronized List<Orders> findAll() throws DaoException {
		List<Orders> result = new ArrayList<Orders>();
		try (Connection connection = ds.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setId(rs.getLong("id"));
				orders.setDescription(rs.getString("description"));
				orders.setClientId(rs.getLong("client_id"));
				orders.setMechanicId(rs.getLong("mechanic_id"));
				orders.setStatus(OrderStatusType.valueOf(rs.getString("status")));
				orders.setDateCreat(rs.getTimestamp("date_creat"));
				orders.setCompletionDate(rs.getTimestamp("completion_date"));
				orders.setPrice(rs.getBigDecimal("price"));
				result.add(orders);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} 		
		return result;
	}
	
	@Override
	public synchronized Orders findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void save(Orders dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}
}
