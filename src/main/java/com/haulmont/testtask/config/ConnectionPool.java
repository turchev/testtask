package com.haulmont.testtask.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCPool;

public final class ConnectionPool {
	private static final Logger LOG = LogManager.getLogger();

	private static final int WAIT_SHUTDOWN_SECONDS = 3;
	private static JDBCPool pool;
	private static DataSource ds;
	private boolean configured = false; // Флаг чтения конфигурационных файлов

	private Properties prop;

	private ConnectionPool() {
		if (configured == false)
			readСonfigurationt();
	}

	private void readСonfigurationt() {
		try {
			prop = PropertiesFactory.getInstans().getPropertiesByKey("ds.prope!!!!!rties");
			String jdbcUrl = prop.getProperty("ds.jdbcUrl");
			String user = prop.getProperty("ds.user");
			String password = prop.getProperty("ds.password");
			int maxPoolSize = Integer.parseInt(prop.getProperty("ds.maxPoolSize"), 10);
			pool = new JDBCPool(maxPoolSize);
			pool.setUrl(jdbcUrl);
			pool.setUser(user);
			pool.setPassword(password);
			ds = pool;
			System.out.println("Свойства ПУЛА: " + prop);
		} catch (Exception e) {
			e.printStackTrace();
			configured = false;
		}

	}

	public Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
//		conn.setAutoCommit(false);
		System.out.println("HSQLDB Connection Created");
		return conn;
	}

	// Проверка соединения с базой данных - успешное, если имя пользователя найдено в сессиях
	public boolean testConnection() {
		try (Connection conn = getConnection(); Statement stmnt = conn.createStatement();) {
			ResultSet rs = stmnt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_SESSIONS " + "WHERE USER_NAME='"
					+ prop.getProperty("ds.user") + "'");
			if (rs.next() == true) {
				return true;
			}
		} catch (SQLException e) {
			LOG.debug(e);
			return false;
		}
		return false;
	}

	public void shutdown() throws SQLException {
		try (Connection conn = getConnection()) {
			try (Statement stmnt = conn.createStatement()) {
				stmnt.execute("SHUTDOWN");
				LOG.debug("running database SHUTDOWN..");
			}
		}
		LOG.debug("closing pool..");
		pool.close(WAIT_SHUTDOWN_SECONDS);
		LOG.debug("closed!");
	}

	public static ConnectionPool getInstance() {
		return SingletonHandler.INSTANCE;
	}

	private static class SingletonHandler {
		private static ConnectionPool INSTANCE = new ConnectionPool();
	}
}
