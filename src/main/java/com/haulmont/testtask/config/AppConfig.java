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
//import com.zaxxer.hikari.HikariConfig;

public class AppConfig {

	public static Logger LOG = Logger.getLogger(AppConfig.class.getName());

	@SuppressWarnings("serial")
	@WebServlet("/*")
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class JdbcExampleVaadinServlet extends VaadinServlet {
	}

	@WebListener
	public static class JdbcExampleContextListener implements ServletContextListener {

		private static final String CONFIG_FILE_DIRECTORY = "etc";
		private static final String LOGGING_FILE_PROPERTIES = "logging.properties";
		private static final String DS_FILE_PROPERTIES = "ds.properties";

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + LOGGING_FILE_PROPERTIES);) {
				LogManager.getLogManager().readConfiguration(stream);
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Logger properties not loaded ", e);
				System.exit(110);
			}

			try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + DS_FILE_PROPERTIES);) {	
				Properties prop = new Properties();
				prop.load(stream);
//				HikariConfig config = new HikariConfig(prop);
				config.validate();
			} catch (Exception e) {
				LOG.log(Level.SEVERE, "Datasources properties not loaded ", e);
				System.exit(111);
			}			
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
		}
	}

}
