package com.haulmont.testtask.connection;

abstract class DsFactoryA {

	public DsFactory getInstans(DsType typeDb) throws DsExeption {
		try {
			switch (typeDb) {
			case HSQLDB:
				return DsHSQLDBimpl.getInstans();
			case H2:
				return DsH2impl.getInstans();
			default:
				throw new IllegalArgumentException(typeDb.toString());
			}
			
		} catch (Exception e) {
			throw new DsExeption(e);
		}
	}
}
