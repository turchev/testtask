package com.haulmont.testtask.config;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.haulmont.testtask.ui.MainUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

public class WebConfig {

	@SuppressWarnings("serial")
	@WebServlet("/*")
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class JdbcExampleVaadinServlet extends VaadinServlet {
	}

	@WebListener
	public static class JdbcExampleContextListener implements ServletContextListener {

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			try {
				new LoggerConfigManager();
			} catch (IOException e) {
				// TODO: handle exception
			}
						
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {						
		}
	}

}
