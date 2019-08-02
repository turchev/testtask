package com.github.turchev.carrepairshop.ds;

import javax.sql.DataSource;

public abstract class DsFactory {

	public static DsFactory getFactory(DsType typeDb) throws DsException {
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
			throw new DsException(e);
		}
	}

	public abstract DataSource getDataSource();

	public abstract void shutdown() throws DsException;

	public abstract boolean testConnection();
}
