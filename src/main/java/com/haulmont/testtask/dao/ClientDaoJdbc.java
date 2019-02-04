package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.Client;

class ClientDaoJdbc implements ClientDao {
	private DataSource ds = null;

	public ClientDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized List<Client> findAll() throws DaoException {
		List<Client> result = new ArrayList<Client>();
		final String SQL = "select * from client;";
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getLong("id"));
				client.setFirstName(rs.getString("first_name"));
				client.setLastName(rs.getString("last_name"));
				client.setPatronnymic(rs.getString("patronnymic"));
				client.setPhone(rs.getString("phone"));
				result.add(client);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public List<String> getLastNameList() throws DaoException {
		List<String> result = new ArrayList<String>();
		final String SQL = "select last_name from client;";
		try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				result.add(rs.getString("last_name"));
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public synchronized Client findById(long id) throws DaoException {
		final String SQL = "select * from client where id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Client client = new Client();
			client.setId(rs.getLong("id"));
			client.setFirstName(rs.getString("first_name"));
			client.setLastName(rs.getString("last_name"));
			client.setPatronnymic(rs.getString("patronnymic"));
			client.setPhone(rs.getString("phone"));
			return client;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public synchronized void update(Client client) throws DaoException {
		final String SQL = "UPDATE client SET last_name=?, first_name=?, patronnymic=?, phone=? WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, client.getLastName());
			pstmt.setString(2, client.getFirstName());
			pstmt.setString(3, client.getPatronnymic());
			pstmt.setString(4, client.getPhone());
			pstmt.setLong(5, client.getId());
			pstmt.execute();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void create(Client client) throws DaoException {
		final String SQL = "INSERT INTO client (last_name, first_name, patronnymic, phone) VALUES (?,?,?,?);";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL)) {
			pstmt.setString(1, client.getLastName());
			pstmt.setString(2, client.getFirstName());
			pstmt.setString(3, client.getPatronnymic());
			pstmt.setString(4, client.getPhone());			
			pstmt.execute();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(long id) throws DaoException {
		// TODO Auto-generated method stub
		
	}
}
