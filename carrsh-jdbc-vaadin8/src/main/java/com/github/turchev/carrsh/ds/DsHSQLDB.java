package com.github.turchev.carrsh.ds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.jdbc.JDBCPool;
import com.github.turchev.carrsh.config.PropertiesFactory;
import com.github.turchev.carrsh.dao.DaoException;

class DsHSQLDB extends DsFactory {
	private static final Logger LOG = LogManager.getLogger();
	private static final int WAIT_SHUTDOWN_SECONDS = 3;
	private static JDBCPool pool;
	private static DataSource ds;
	private static Properties prop;
	private static String jdbcUrl;

	private DsHSQLDB() {
	}

	public static DsFactory getInstans() throws DaoException {
		if (ds == null)
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
				LOG.debug("Creating an HSQLDB Instance Data Source Factory");
			} catch (Exception e) {
				LOG.error(e);
				throw new DaoException(e);
			}
		return SingletonHandler.INSTANCE;
	}

	@Override
	public DataSource getDataSource() {
		return ds;
	}

	@Override
	public void shutdown() throws DsException {
		try (Connection conn = ds.getConnection(); Statement stmnt = conn.createStatement();) {
			stmnt.execute("SHUTDOWN");
			LOG.debug("running database SHUTDOWN..");
			pool.close(WAIT_SHUTDOWN_SECONDS);
		} catch (Exception e) {
			throw new DsException(e);
		}
	}

	@Override
	// Проверка соединения с базой данных - успешное, если имя пользователя найдено
	// в сессиях
	public boolean testConnection() {
		try (Connection conn = ds.getConnection(); Statement stmnt = conn.createStatement();) {
			ResultSet rs = stmnt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_SESSIONS " + "WHERE USER_NAME='"
					+ prop.getProperty("ds.user") + "'");
			if (rs.next() == true) {
				return true;
			}
		} catch (Exception e) {
			LOG.debug("Test connection failed: \n ", e);
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return  pool.getDescription() +" "+ pool.getURL();
	}

	private static class SingletonHandler {
		private static DsHSQLDB INSTANCE = new DsHSQLDB();
	}
}
