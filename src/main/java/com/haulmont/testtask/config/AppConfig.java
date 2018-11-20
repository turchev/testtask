package com.haulmont.testtask.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//import javax.servlet.annotation.WebServlet;
//
//import com.haulmont.testtask.ui.MainUI;
//import com.vaadin.annotations.VaadinServletConfiguration;
//import com.vaadin.server.VaadinServlet;

public class AppConfig {

//	@SuppressWarnings("serial")
//	@WebServlet("/*")
//	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
//	public static class JdbcExampleVaadinServlet extends VaadinServlet {
//	}

	@WebListener
	public static class JdbcExampleContextListener implements ServletContextListener {

		public static Logger LOG = Logger.getLogger(JdbcExampleContextListener.class.getName());

		private static final String CONFIG_FILE_DIRECTORY = "etc";
		private static final String LOGGING_FILE_PROPERTIES = "logging.properties";

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + LOGGING_FILE_PROPERTIES);) {
				LogManager.getLogManager().readConfiguration(stream);
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Logger properties not loaded ", e);
				System.exit(110);
			}

			
			try {
				if (ConnectionPool.getInstance().testConnection()) {
					System.out.println("Соединение установлено");
				} else {
					System.out.println("Соединение не установлено");
				}
			} catch (ConfigExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			try (Connection conn = ConnectionPool.getInstance().getConnection();
//					Statement stmnt = conn.createStatement();) {
//				ResultSet rs = stmnt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.SYSTEM_SESSIONS " + "WHERE USER_NAME='SA'");				
//				System.out.println(rs.next());
//			} catch (Exception e) {
//				LOG.log(Level.SEVERE, "Test connection to database not established ", e);
//				System.exit(111);
//			}
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {

		}
	}

}
