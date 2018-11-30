package com.haulmont.testtask.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PropertiesFactory {

	private static final Logger LOG = LogManager.getLogger();
	private static Map<String, Properties> propHashMap = new HashMap<String, Properties>();
	private static final String CONFIG_DIRECTORY = "etc"; // Директория для конфигурационных файлов
	private static boolean configured = false; // Флаг готовности

	private PropertiesFactory() {
	}

	public Map<String, Properties> getPropHashMap() {
		return propHashMap;
	}
	
	public Properties getPropertiesByKey(String shortFileName) {
		return propHashMap.get(shortFileName);
	}

	public static PropertiesFactory getInstans() throws ConfigException {		
		if (configured == false) {			
			try {
				File configDir = new File(CONFIG_DIRECTORY);
				String[] files = configDir.list();
				for (String fileName : files) {
					LOG.debug("Configuration File: {}", fileName);
					try (FileInputStream fis = new FileInputStream(CONFIG_DIRECTORY + "/" + fileName);) {
						Properties prop = new Properties();
						fileName = fileName.toLowerCase();
						if (fileName.endsWith(".properties")) {
							prop.load(fis);
							propHashMap.put(fileName, prop);
							continue;
						}
						if (fileName.endsWith(".xml")) {
							prop.loadFromXML(fis);
							propHashMap.put(fileName, prop);
							continue;
						}
					}
				}
			} catch (Exception e) {
				propHashMap.clear();
				configured = false;
				throw new ConfigException(e);				
			}			
			configured = true;
		}		
		return SingletonHandler.INSTANS;
	}

	private static final class SingletonHandler {
		private static PropertiesFactory INSTANS = new PropertiesFactory();
	}
}
