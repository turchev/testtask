package com.haulmont.testtask.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

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

		public static Logger LOG = Logger.getLogger(AppServletContextListener.class.getName());

		private static final String CONFIG_FILE_DIRECTORY = "etc";
		private static final String LOGGING_FILE_PROPERTIES = "logging.properties";

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			
			ConfigFactory cfgMap = ConfigFactory.getInstans();
			if (cfgMap.isConfigured() == false) {
				LOG.severe("Logger properties not loaded ");
				System.exit(110);
			}
//			LogManager.getLogManager().
			
//			try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + LOGGING_FILE_PROPERTIES);) {
//				LogManager.getLogManager().readConfiguration(stream);
//			} catch (IOException e) {
//				LOG.log(Level.SEVERE, "Logger properties not loaded ", e);
//				System.exit(110);
//			}
//
//			try {
//				if (ConnectionPool.getInstance().testConnection() == false) {
//					LOG.log(Level.ALL, "Test connection to database not established ");					
//					System.exit(111);
//				}
//			} catch (ConfigExeption e) {
//				e.printStackTrace();
//				System.exit(112);
//			}
//			LOG.info("Test database connection established ");
//			System.out.println();
			
			
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			
		}
	}
}
