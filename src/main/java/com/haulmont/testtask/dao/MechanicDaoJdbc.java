package com.haulmont.testtask.dao;

import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.Mechanic;

class MechanicDaoJdbc implements MechanicDao {

	public MechanicDaoJdbc(DataSource ds) {
	}

	@Override
	public Mechanic findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mechanic> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Mechanic dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}	
}
