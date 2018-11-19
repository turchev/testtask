package com.haulmont.testtask.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public final class ConnectionPool {
	public static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

	private static final String CONFIG_FILE_DIRECTORY = "etc";
	private static final String DS_FILE_PROPERTIES = "ds.properties";
	private static final String DEFAULT_JDBC_URL = "jdbc:hsqldb:file:db/car_service_db;shutdown=true;ifexists=true";
	private static final String DEFAULT_USER = "SA";
	private static final String DEFAULT_PASSWORD = "";
	private static final String DEFAULT_MAX_POOL_SIZE = "8";

	protected static DataSource ds;

	private ConnectionPool() {
	}

	public static ConnectionPool getInstance() throws ConfigExeption {
		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + DS_FILE_PROPERTIES);) {
			Properties prop = new Properties();
			prop.load(stream);
			String jdbcUrl = prop.getProperty("ds.jdbcUrl", DEFAULT_JDBC_URL);
			String user = prop.getProperty("ds.user", DEFAULT_USER);
			String password = prop.getProperty("ds.password", DEFAULT_PASSWORD);
			int maxPoolSize = Integer.parseInt(prop.getProperty("ds.maxPoolSize", DEFAULT_MAX_POOL_SIZE), 10);
			JDBCPool pool = new JDBCPool(maxPoolSize);
			pool.setUrl(jdbcUrl);
			pool.setUser(user);
			pool.setPassword(password);
			ds = pool;
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Datasources properties not loaded ", e);
			throw new ConfigExeption(e);
		}
		return SingletonHandler.INSTANCE;
	}

	private static class SingletonHandler {
		private static ConnectionPool INSTANCE = new ConnectionPool();
	}

	public DataSource getDataSoursce() {
		return ds;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
//		conn.setAutoCommit(false);
		System.out.println("HSQLDB Connection Created");
		return conn;
	}
}
