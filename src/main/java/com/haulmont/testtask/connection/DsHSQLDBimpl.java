package com.haulmont.testtask.connection;

import javax.sql.DataSource;

public class DsHSQLDBimpl extends DsFactoryA implements DsFactory{

	private DsHSQLDBimpl(){		
	}
		
	public static DsFactory getInstans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean testConnection() {
		// TODO Auto-generated method stub
		return false;
	}	
}
