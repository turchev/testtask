package com.haulmont.testtask.dao;

import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;
import com.haulmont.testtask.ds.DsFactory;
import com.haulmont.testtask.ds.DsType;

class DaoFactoryHSQLDB extends DaoFactory {	
	private static JDBCPool pool;
	private static DataSource ds;

	private DaoFactoryHSQLDB() {
	}

	public static DaoFactoryHSQLDB getInstance() throws DaoException {		
		try {
			ds = DsFactory.getFactory(DsType.HSQLDB).getDataSource();
		} catch (Exception e) {
			throw new DaoException(e);
		}

		return SingletonHandler.INSTANCE;
	}

	@Override
	public ClientDao getClientDAO() throws DaoException {
		return new ClientDaoJdbc(ds);
	}

	@Override
	public MechanicDao getMechanicDao() throws DaoException {
		return new MechanicDaoJdbc(ds);
	}

	@Override
	public OrdersDao getOrdersDao() throws DaoException {
		return new OrdersDaoJdbc(ds);
	}

	@Override
	public String toString() {
		return "DaoFactoryHSQLDB: " + pool.getDescription();
	}

	private static class SingletonHandler {
		private static DaoFactoryHSQLDB INSTANCE = new DaoFactoryHSQLDB();
	}
}
