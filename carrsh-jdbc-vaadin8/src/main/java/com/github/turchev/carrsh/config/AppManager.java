package com.github.turchev.carrsh.config;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrsh.MainUI;
import com.github.turchev.carrsh.ds.DsFactory;
import com.github.turchev.carrsh.ds.DsType;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

public class AppManager {

	@SuppressWarnings("serial")
	@WebServlet("/*")
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class AppVaadinServlet extends VaadinServlet {
	}

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
				// Загрузка конфигурации с файлов (некоторые не требуются в программе, используются просто
				// для тестирования)				
				propFactory = PropertiesFactory.getInstans();
				for (String key : propFactory.getPropHashMap().keySet()) {
					LOG.debug("Uploaded property files: {}", key);					
				}
				
				// Проверка пула соединений с базой данных HSQLDB	
				dsfHSQLDB = DsFactory.getFactory(DsType.HSQLDB);
				if (dsfHSQLDB.testConnection() == true) {
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
