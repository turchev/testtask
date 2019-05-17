package com.haulmont.carrepairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.haulmont.carrepairshop.domain.orders.OrderStatusType;
import com.haulmont.carrepairshop.domain.orders.OrdersWithFio;

class OrdersDaoJdbc implements OrdersDao {

	private DataSource ds = null;

	public OrdersDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized List<OrdersWithFio> findAll() throws DaoException {
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
				orders.setDateCreat(rs.getTimestamp("date_creat").toLocalDateTime());
				orders.setCompletionDate(rs.getTimestamp("completion_date").toLocalDateTime());
				orders.setPrice(rs.getBigDecimal("price"));
				result.add(orders);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public synchronized List<OrdersWithFio> findUsingFilter(String findDescription, String status, String clientFio)
			throws DaoException {

		List<OrdersWithFio> result = new ArrayList<OrdersWithFio>();

		String sqlLikeDescription, sqlLikeStatus, sqlLikeClientFio;
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {

			if (findDescription == null || findDescription.isEmpty()) {
				sqlLikeDescription = "'%'";
			} else {
				sqlLikeDescription = "'%" + findDescription + "%'";
			}
			if (status == null || status.isEmpty()) {
				sqlLikeStatus = "'%'";
			} else {
				sqlLikeStatus = "'%" + status + "%'";
			}
			if (clientFio == null || clientFio.isEmpty()) {
				sqlLikeClientFio = "'%'";
			} else {
				sqlLikeClientFio = "'%" + clientFio + "%'";
			}

			String sqlResult = "SELECT * FROM orders_with_fio WHERE LOWER(description) LIKE LOWER(" + sqlLikeDescription
					+ ") AND LOWER(status) LIKE LOWER(" + sqlLikeStatus + ") AND LOWER(client_fio) LIKE LOWER("
					+ sqlLikeClientFio + ");";

			ResultSet rs = statement.executeQuery(sqlResult);
			while (rs.next()) {
				OrdersWithFio orders = new OrdersWithFio();
				orders.setId(rs.getLong("id"));
				orders.setDescription(rs.getString("description"));
				orders.setClientId(rs.getLong("client_id"));
				orders.setMechanicId(rs.getLong("mechanic_id"));
				orders.setClientFio(rs.getString("client_fio"));
				orders.setMechanicFio(rs.getString("mechanic_fio"));
				orders.setStatus(OrderStatusType.valueOf(rs.getString("status")));
				orders.setDateCreat(rs.getTimestamp("date_creat").toLocalDateTime());
				orders.setCompletionDate(rs.getTimestamp("completion_date").toLocalDateTime());
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
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM orders_with_fio where id=?;");) {
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
			orders.setDateCreat(rs.getTimestamp("date_creat").toLocalDateTime());
			orders.setCompletionDate(rs.getTimestamp("completion_date").toLocalDateTime());
			orders.setPrice(rs.getBigDecimal("price"));
			return orders;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void update(OrdersWithFio order) throws DaoException {
		final String SQL = "UPDATE orders SET description=?, client_id=?, mechanic_id=?, status=?, date_creat=?, completion_date=?, price=? WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, order.getDescription());
			pstmt.setLong(2, order.getClientId());
			pstmt.setLong(3, order.getMechanicId());
			pstmt.setString(4, order.getStatus().toString());
			pstmt.setTimestamp(5, Timestamp.valueOf(order.getDateCreat()));
			pstmt.setTimestamp(6, Timestamp.valueOf(order.getCompletionDate()));
			pstmt.setBigDecimal(7, order.getPrice());
			pstmt.setLong(8, order.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void create(OrdersWithFio order) throws DaoException {
		final String SQL = "INSERT INTO orders (description, client_id, mechanic_id, status, date_creat, completion_date, price) VALUES (?,?,?,?,?,?,?);";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, order.getDescription());
			pstmt.setLong(2, order.getClientId());
			pstmt.setLong(3, order.getMechanicId());
			pstmt.setString(4, order.getStatus().toString());
			pstmt.setTimestamp(5, Timestamp.valueOf(order.getDateCreat()));
			pstmt.setTimestamp(6, Timestamp.valueOf(order.getCompletionDate()));
			pstmt.setBigDecimal(7, order.getPrice());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void delete(long id) throws DaoException {
		final String SQL = "DELETE FROM orders WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
