package com.haulmont.testtask.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCPool;
import com.haulmont.testtask.config.PropertiesFactory;

public class DaoFactoryHSQLDB extends DaoFactory {
	private static final Logger LOG = LogManager.getLogger();
	private static final int WAIT_SHUTDOWN_SECONDS = 3;
	private static JDBCPool pool;
	private static DataSource ds;
	private static boolean configured = false; // Флаг готовности
	private static Properties prop;
	private static String jdbcUrl;

	private DaoFactoryHSQLDB() {
	}

	public static DaoFactoryHSQLDB getInstance() throws DaoException {
		// Начальная конфигурация пула HSQLDB
		if (configured == false)
			try {
				prop = PropertiesFactory.getInstans().getPropertiesByKey("ds.properties");
				jdbcUrl = prop.getProperty("ds.jdbcUrl");
				String user = prop.getProperty("ds.user").trim();
				String password = prop.getProperty("ds.password");
				int maxPoolSize = Integer.parseInt(prop.getProperty("ds.maxPoolSize"), 10);
				pool = new JDBCPool(maxPoolSize);
				pool.setUrl(jdbcUrl);
				pool.setUser(user);
				pool.setPassword(password);
				ds = pool;				
			} catch (Exception e) {
				throw new DaoException(e);
			}
		configured = true;
		return SingletonHandler.INSTANCE;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
//		conn.setAutoCommit(false);
		LOG.debug("HSQLDB Connection Created");
		return conn;
	}	

	@Override
	public ClientDao getClientDAO() throws DaoException {
		return new ClientDaoJdbc(ds);
	}

	@Override
	public MechanicDao getMechanicDao() throws DaoException {
		return new MechanicDaoJdbc(ds);
	}

	@Override
	public OrdersDao getOrderDao() throws DaoException {
		return new OrdersDaoJdbc(ds);
	}
	
	@Override
	public String toString() {
		return "DaoFactoryHSQLDB: " + pool.getDescription();
	}
	
	// Проверка соединения с базой данных - успешное, если имя пользователя найдено
		// в сессиях
		public boolean testConnection() {
			try (Connection conn = getConnection(); Statement stmnt = conn.createStatement();) {
				ResultSet rs = stmnt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_SESSIONS " + "WHERE USER_NAME='"
						+ prop.getProperty("ds.user") + "'");
				if (rs.next() == true) {
					return true;
				}
			} catch (Exception e) {
				LOG.error("Test connection failed: \n ", e);
				return false;
			}
			return false;
		}

		public void shutdown() throws SQLException {
			try (Connection conn = getConnection(); Statement stmnt = conn.createStatement();) {
				stmnt.execute("SHUTDOWN");
				LOG.debug("running database SHUTDOWN..");
			}
			pool.close(WAIT_SHUTDOWN_SECONDS);
			LOG.debug("closed!");
		}
	
	private static class SingletonHandler {
		private static DaoFactoryHSQLDB INSTANCE = new DaoFactoryHSQLDB();
	}
}
