package com.github.turchev.carrsh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.github.turchev.carrsh.domain.ShortName;
import com.github.turchev.carrsh.domain.person.Client;

class ClientDaoJdbc implements ClientDao {
	private DataSource ds = null;

	public ClientDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized List<Client> findAll() throws DaoException {
		List<Client> result = new ArrayList<Client>();
		final String SQL = "SELECT * FROM client;";
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
	public synchronized List<String> getLastNameList() throws DaoException {
		List<String> result = new ArrayList<String>();
		final String SQL = "SELECT last_name FROM client;";
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
		final String SQL = "SELECT * FROM client where id=?;";
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
	public synchronized void create(Client client) throws DaoException {
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
	public synchronized void delete(long id) throws DaoException {
		final String SQL = "DELETE FROM client WHERE id=?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(SQL);) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException ex) {
			throw new DaoException("Предварительно удалите все заказы, связанные с этой записью");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ShortName<Client> getFioById(Long id) throws DaoException {
		Client client = findById(id);
		ShortName<Client> clientShortName = new ShortName<Client>(client);
		return clientShortName;
	}

	@Override
	public List<ShortName<Client>> findAllShortName() throws DaoException {
		List<Client> client = findAll();
		List<ShortName<Client>> clientShortName = new ArrayList<ShortName<Client>>();
		for (Client itrClient : client) {
			clientShortName.add(new ShortName<Client>(itrClient));
		}
		return clientShortName;
	}
}
