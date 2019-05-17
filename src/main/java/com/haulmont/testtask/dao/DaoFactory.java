package com.haulmont.carrepairshop.dao;

import com.haulmont.carrepairshop.ds.DsType;

public abstract class DaoFactory {

	public static DaoFactory getFactory(DsType type) throws DaoException {
		try {
			switch (type) {
			case HSQLDB:
				return DaoFactoryHSQLDB.getInstance();
			case H2:
				return DaoFactoryH2.getInstance();
			default:
				throw new IllegalArgumentException(type.toString());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public abstract ClientDao getClientDao() throws DaoException;

	public abstract MechanicDao getMechanicDao() throws DaoException;

	public abstract OrdersDao getOrdersDao() throws DaoException;

}
