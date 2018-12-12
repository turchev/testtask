package com.haulmont.testtask.dao;

import java.util.List;

import javax.sql.DataSource;
import com.haulmont.testtask.entity.Client;

public class ClientDaoJdbc implements ClientDao {
	private DataSource ds = null;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Client dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}

	@Override
	public void shutdown() throws DaoException {
		// TODO Auto-generated method stub
	}

}
