package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.haulmont.testtask.entity.Client;

public class ClientDaoJdbc implements ClientDao {
	private DataSource ds = null;
	private static final String SELECT_ALL = "SELECT * FROM client";

	public ClientDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Client findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() throws DaoException {
		List<Client> result = new ArrayList<Client>();
		try (Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL);) {
			ResultSet rs = statement.executeQuery();
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
	public void save(Client dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}
}
