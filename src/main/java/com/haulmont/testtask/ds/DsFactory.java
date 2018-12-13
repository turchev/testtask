package com.haulmont.testtask.ds;

import javax.sql.DataSource;

public abstract class DsFactory {

	public static DsFactory getFactory(DsType typeDb) throws DsExeption {
		try {
			switch (typeDb) {
			case HSQLDB:
				return DsHSQLDB.getInstans();
			case H2:
				return DsH2.getInstans();
			default:
				throw new IllegalArgumentException(typeDb.toString());
			}

		} catch (Exception e) {
			throw new DsExeption(e);
		}
	}

	public abstract DataSource getDataSource();

	public abstract void shutdown() throws DsExeption;

	public abstract boolean testConnection();
}
