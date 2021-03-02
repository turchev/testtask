package com.github.turchev.carrepairshop.config;

import java.util.Locale;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.turchev.carrepairshop.ds.DsFactory;
import com.github.turchev.carrepairshop.ds.DsType;

public class AppManager {

	@WebListener
	public static class AppServletContextListener implements ServletContextListener {
		private static final Logger LOG = LogManager.getLogger();
		PropertiesFactory propFactory;
		DsFactory dsfHSQLDB;

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			LOG.debug("Application initialization");
			try {
				Locale.setDefault(new Locale("ru", "RU"));

				// Loading configuration from files (some are not required in the program, they are used only for testing)
				propFactory = PropertiesFactory.getInstans();
				for (String key : propFactory.getPropHashMap().keySet()) {
					LOG.debug("Uploaded property files: {}", key);					
				}
				
				// Checking the connection pool with the HSQLDB database
				dsfHSQLDB = DsFactory.getFactory(DsType.HSQLDB);
				if (dsfHSQLDB.testConnection()) {
					LOG.debug("Connection pool created: {}", dsfHSQLDB.toString());
				} else {
					throw new ConfigException(
							"Test database connection not established. If the database has not been created yet, "
									+ "clear the 'db' directory, set the property 'ifexists=false' and run the program again. ");
				}

			} catch (Exception e) {
				LOG.error("Application failed initialization ", e);
				System.exit(100);
			}						
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			try {
				dsfHSQLDB.shutdown();
				LOG.debug("Application shutdown");
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}
}
