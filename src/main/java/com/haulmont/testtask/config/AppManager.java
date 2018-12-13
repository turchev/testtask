package com.haulmont.testtask.config;

import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.haulmont.testtask.connection.DsType;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ui.MainUI;
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
		DaoFactory hsqlDaoFactory;

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			try {				
				// Загрузка конфигурации с файлов (некоторые не требуются в программе, просто
				// для тестирования)
				propFactory = PropertiesFactory.getInstans();
				for (String key : propFactory.getPropHashMap().keySet()) {
					System.out.println("Uploaded property files: " + key);
				}
				// Проверка пула соединений с базой данных HSQLDB
				hsqlDaoFactory = DaoFactory.get(DsType.HSQLDB);			
				if (hsqlDaoFactory.testConnection() == true) {
					LOG.debug("Connection pool created: {}", hsqlDaoFactory.toString());
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
				hsqlDaoFactory.shutdown();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
