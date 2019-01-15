package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.OrderStatusType;
import com.haulmont.testtask.entity.OrdersWithFio;

class OrdersDaoJdbc implements OrdersDao {
	private DataSource ds = null;
	private static final String SELECT_ALL = "select * from orders_with_fio;";
	private static final String SELECT_BY_ID = "select * from orders where id=?;";

	public OrdersDaoJdbc(DataSource ds) {
		this.ds = ds;
	}	

	@Override
	public synchronized List<OrdersWithFio> findAll() throws DaoException {
		
//		CREATE VIEW orders_with_fio AS
//		SELECT orders.id, orders.description, orders.client_id, orders.mechanic_id, orders.status, orders.date_creat, orders.completion_date, orders.price,
//			CONCAT(client.first_name, ' ', client.last_name, ' ', client.patronnymic) AS client_fio,
//			CONCAT(mechanic.first_name, ' ', mechanic.last_name) AS mechanic_fio
//			FROM orders
//		LEFT JOIN client ON orders.client_id = client.id
//		LEFT JOIN mechanic ON orders.mechanic_id = mechanic.id;		
		
		List<OrdersWithFio> result = new ArrayList<OrdersWithFio>();
		try (Connection connection = ds.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SELECT_ALL);			
			while (rs.next()) {
				OrdersWithFio orders = new OrdersWithFio();
				orders.setId(rs.getLong("id"));
				orders.setDescription(rs.getString("description"));
				orders.setClientId(rs.getLong("client_id"));
				orders.setMechanicId(rs.getLong("mechanic_id"));
				orders.setClientFio(rs.getString("client_fio"));
				orders.setMechanicFio(rs.getString("mechanic_fio"));
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
	public synchronized OrdersWithFio findById(long id) throws DaoException {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			OrdersWithFio orders = new OrdersWithFio();
			orders.setId(rs.getLong("id"));
			orders.setDescription(rs.getString("description"));
			orders.setClientId(rs.getLong("client_id"));
			orders.setMechanicId(rs.getLong("mechanic_id"));
			orders.setClientFio(rs.getString("client_fio"));
			orders.setMechanicFio(rs.getString("mechanic_fio"));
			orders.setStatus(OrderStatusType.valueOf(rs.getString("status")));
			orders.setDateCreat(rs.getTimestamp("date_creat"));
			orders.setCompletionDate(rs.getTimestamp("completion_date"));
			orders.setPrice(rs.getBigDecimal("price"));	
			return orders;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void save(OrdersWithFio dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}
}
