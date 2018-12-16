package com.haulmont.testtask.dao;

import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.Mechanic;

class MechanicDaoJdbc implements MechanicDao {

	public MechanicDaoJdbc(DataSource ds) {
	}

	@Override
	public synchronized Mechanic findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized List<Mechanic> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void save(Mechanic dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}	
}
