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

	private String jdbcUrl = "jdbc:hsqldb:file:db/car_service_db;shutdown=true;ifexists=true";
	private String user = "SA";
	private String password = "";
	private int maxPoolSize = 8;

	private static DataSource ds;

	private ConnectionPool() throws ConfigExeption {
		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + DS_FILE_PROPERTIES);) {
			Properties prop = new Properties();
			prop.load(stream);
			jdbcUrl = prop.getProperty("ds.jdbcUrl");
			user = prop.getProperty("ds.user");
			password = prop.getProperty("ds.password");
			maxPoolSize = Integer.parseInt(prop.getProperty("ds.maxPoolSize"), 10);
			JDBCPool pool = new JDBCPool(maxPoolSize);
			pool.setUrl(jdbcUrl);
			pool.setUser(user);
			pool.setPassword(password);
			ds = pool;
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Datasources properties not loaded ", e);
			throw new ConfigExeption(e);
		}
	}

	public static DataSource getDataSoursce() {
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
//		conn.setAutoCommit(false);
		return conn;
	}
}
