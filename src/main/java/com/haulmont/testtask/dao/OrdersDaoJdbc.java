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

	public OrdersDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<OrdersWithFio> findAll() throws DaoException {
		List<OrdersWithFio> result = new ArrayList<OrdersWithFio>();
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery("SELECT * FROM orders_with_fio;");
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
	public List<OrdersWithFio> findUsingFilter(String findDescription, String status, String clientFio) throws DaoException {
		List<OrdersWithFio> result = new ArrayList<OrdersWithFio>();	
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM orders_with_fio "
						+ "WHERE LOWER(description) LIKE LOWER('%" + findDescription + "%') "
						+ "AND status = ? "
						+ "AND LOWER(client_fio) LIKE LOWER('%" + clientFio + "%');");
				) {					
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
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
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM orders where id=?;");) {
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
