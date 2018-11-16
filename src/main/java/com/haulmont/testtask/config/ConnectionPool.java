package com.haulmont.testtask.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {

	private static final int INIT_POOL_SIZE = 8;
	private static final int WAIT_SHUTDOWN_SECONDS = 3;
	private static final String USERNAME = "SA";
	private static final String PASSWORD = "";

	private static JDBCPool pool;

	private static DataSource getDatasource() {
		if (pool == null) {
			// setup datasource if null
			synchronized (ConnectionPool.class) {
				Logger.log("intializing hsqldb JDBCPool with a size of " + INIT_POOL_SIZE);
				if (pool == null) {
					pool = new JDBCPool(INIT_POOL_SIZE);
					pool.setUrl("jdbc:hsqldb:file:/opt/db/testdb;ifexists=false");
					pool.setUser(USERNAME);
					pool.setPassword(PASSWORD);
				}
			}
		}

		return pool;
	}

	public static Connection getConnection() throws SQLException {
		DataSource ds = (JDBCPool) getDatasource();
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public static void shutdown() throws SQLException {
		try (Connection conn = getConnection()) {
			try (Statement stmnt = conn.createStatement()) {
				stmnt.execute("SHUTDOWN");
				Logger.log("running database SHUTDOWN..");
			}
		}
		Logger.log("closing pool..");
		pool.close(WAIT_SHUTDOWN_SECONDS);
		Logger.log("closed!");
	}
}
