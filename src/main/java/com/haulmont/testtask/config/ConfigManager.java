//package com.haulmont.testtask.config;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.logging.LogManager;
//
//import com.haulmont.testtask.config.ConfigExeptionHelper.*;
//
//
//public final class ConfigManager extends AbstractConfig {	
//	
//	private static final String LOGGING_FILE_PROPERTIES = "logging.properties";
//	private static final String DB_FILE_PROPERTIES = "logging.properties";
//
//	public ConfigManager() {
//	}
//	
//	public static void LogLoadConfig() throws LogLoadExeption {		
//		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + LOGGING_FILE_PROPERTIES);) {
//			LogManager.getLogManager().readConfiguration(stream);
//		} catch (IOException e) {
//			throw new ConfigExeptionHelper.LogLoadExeption(e);			
//		}		
//	}
//	
//	public static void DBLoadConfig() throws DBLoadExeption {		
//		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + DB_FILE_PROPERTIES);) {
//			LogManager.getLogManager().readConfiguration(stream);
//		} catch (IOException e) {
//			throw new ConfigExeptionHelper.DBLoadExeption(e);			
//		}		
//	}
//	
//}
