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
	private static final String SELECT_ALL_CLIENT = "select * from client;";
	private static final String SELECT_BY_ID = "select * from client where id=?;";;

	public ClientDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized List<Client> findAll() throws DaoException {
		List<Client> result = new ArrayList<Client>();
		try (Connection connection = ds.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SELECT_ALL_CLIENT);
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
	public synchronized Client findById(long id) throws DaoException {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);) {
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
	public synchronized void save(Client dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}
}
