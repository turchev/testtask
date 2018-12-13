package com.haulmont.testtask.connection;

import javax.sql.DataSource;

public interface DsFactory {

	DataSource getDataSource();

	void shutdown();

	boolean testConnection();
	
}
