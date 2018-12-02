package com.haulmont.testtask.config;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		PropertiesFactory propFactory = null;
		ConnectionPool conPool = null;

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			try {
				// Загрузка конфигурации с файлов (некоторые не требуются в программе, просто для тестирования)
				propFactory = PropertiesFactory.getInstans();
				for (String key : propFactory.getPropHashMap().keySet()) {
				    System.out.println("Uploaded property files: " + key);
				}
				// Установка пула соединений с базой данных HSQLDB
				conPool = ConnectionPool.getInstance();					
				if (conPool.testConnection() == true) {
					LOG.debug("Connection pool created: {}", conPool.getPool().getDescription());
				} else {
					throw new ConfigException("Test connection to database not established ");					
				}
				
			} catch (Exception e) {
				LOG.error("Application failed initialization ", e);
				System.exit(100);
			}
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			try {
				conPool.shutdown();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
