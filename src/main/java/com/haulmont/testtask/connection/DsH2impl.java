package com.haulmont.testtask.connection;

import javax.sql.DataSource;

public class DsH2impl extends DsFactoryA implements DsFactory{

	public static DsFactory getInstans() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DsH2impl() {
		// TODO Auto-generated constructor stub
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
