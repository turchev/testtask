package com.haulmont.testtask.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public final class ConnectionPool {
	public static Logger LOG = Logger.getLogger(ConnectionPool.class.getName());

	private static final int WAIT_SHUTDOWN_SECONDS = 3;
	private static final String CONFIG_FILE_DIRECTORY = "etc";
	private static final String DS_FILE_PROPERTIES = "ds.properties";
	private static JDBCPool pool;
	private static DataSource ds;
	private static Properties prop;

	private ConnectionPool() {
	}

	public static ConnectionPool getInstance() throws ConfigExeption {
		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + DS_FILE_PROPERTIES);) {
			prop = new Properties();
			prop.load(stream);
			String jdbcUrl = prop.getProperty("ds.jdbcUrl");
			String user = prop.getProperty("ds.user");
			String password = prop.getProperty("ds.password");
			int maxPoolSize = Integer.parseInt(prop.getProperty("ds.maxPoolSize"), 10);
			pool = new JDBCPool(maxPoolSize);
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

	public Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
//		conn.setAutoCommit(false);
		System.out.println("HSQLDB Connection Created");
		return conn;
	}

	public boolean testConnection() {
		try (Connection conn = getConnection(); Statement stmnt = conn.createStatement();) {
			ResultSet rs = stmnt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_SESSIONS " + "WHERE USER_NAME='"
					+ prop.getProperty("ds.user") + "'");
			if (rs.next() == true) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public void shutdown() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmnt = conn.createStatement()) {
                stmnt.execute("SHUTDOWN");
                LOG.log(Level.ALL, "running database SHUTDOWN..");
            }
        }
        LOG.log(Level.ALL, "closing pool..");
        pool.close(WAIT_SHUTDOWN_SECONDS);
        LOG.log(Level.ALL, "closed!");
	}

	private static class SingletonHandler {
		private static ConnectionPool INSTANCE = new ConnectionPool();
	}
}
