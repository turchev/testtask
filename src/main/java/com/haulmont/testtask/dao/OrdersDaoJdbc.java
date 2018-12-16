package com.haulmont.testtask.dao;

import java.util.List;
import javax.sql.DataSource;
import com.haulmont.testtask.entity.Orders;

class OrdersDaoJdbc implements OrdersDao {

	public OrdersDaoJdbc(DataSource ds) {
	}

	@Override
	public synchronized Orders findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized List<Orders> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void save(Orders dataSet) throws DaoException {
		// TODO Auto-generated method stub
	}
}
