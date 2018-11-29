package com.haulmont.testtask.config;

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

		@Override
		public void contextInitialized(ServletContextEvent sce) {

			PropertiesFactory cfgMap = PropertiesFactory.getInstans();
			if (cfgMap.isConfigured() == false) {
				LOG.error("Properties not loaded ");
				System.exit(110);
			}
			LOG.debug("Properties loaded: {}", cfgMap.toString());

			if (ConnectionPool.getInstance().testConnection() == false) {
				LOG.error("Test connection to database not established ");
				System.exit(111);
			}
			LOG.info("Test database connection established ");
			System.out.println();

		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {

		}
	}
}
