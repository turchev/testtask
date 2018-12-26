package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.Mechanic;

class MechanicDaoJdbc implements MechanicDao {

	private DataSource ds = null;
	private static final String SELECT_ALL = "select * from mechanic;";
	
	public MechanicDaoJdbc(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public synchronized Mechanic findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized List<Mechanic> findAll() throws DaoException {
		List<Mechanic> result = new ArrayList<Mechanic>();
		try (Connection connection = ds.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Mechanic mechanic = new Mechanic();
				mechanic.setId(rs.getLong("id"));
				mechanic.setFirstName(rs.getString("first_name"));
				mechanic.setLastName(rs.getString("last_name"));
				mechanic.setPatronnymic(rs.getString("patronnymic"));
				mechanic.setWages(rs.getBigDecimal("wages"));
				result.add(mechanic);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} 		
		return result;
	}

	@Override
	public synchronized void save(Mechanic dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}	
}
