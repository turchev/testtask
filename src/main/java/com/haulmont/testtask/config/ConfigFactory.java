package com.haulmont.testtask.config;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public final class ConfigFactory {

	public static Logger LOG = Logger.getLogger(ConfigFactory.class.getName());
	private static final String CONFIG_DIRECTORY = "etc"; // Initializing the default configuration directory

	private Properties propertiesFull = new Properties();
	private Map<String, Properties> propMap = new HashMap<String, Properties>();

	private ConfigFactory() {
	}

	public static ConfigFactory getInstans() {
		return ConfigHandler.INSTANS;
	}

	public Properties getPropertiesFull() {
		File configDir = new File(CONFIG_DIRECTORY);
		final String[] mask = { ".xml", ".properties" };
		String[] files = configDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File folder, String name) {
				for (String s : mask)
					if (name.toLowerCase().endsWith(s))
						return true;

				return false;
			}
		});
		for (String fileName : files)
			System.out.println("File: " + fileName);

		return propertiesFull;
	}

	public Properties getPropertiesByName(String fileName) {
		return null;
	}

	private static final class ConfigHandler {
		private static ConfigFactory INSTANS = new ConfigFactory();
	}
}
//private static Properties loadProperties(final String cfg) {
//
//	Properties propertiesResult = new Properties();
//	File configDir = new File(cfg);
//	String[] files = null;
//	
//	files = configDir.list();

//
//	files = configDir.list((File folder, String name) -> {
//
//		String fileName = cfg + "/" + name;				
//		Properties prp = new Properties();
//		try (FileInputStream fis = new FileInputStream(fileName);){			
//
//			if (name.toLowerCase().endsWith(".xml")) {
//				prp.loadFromXML(fis);
//				propertiesResult.putAll(prp);
//				return true;
//			}
//			if (name.toLowerCase().endsWith(".properties")) {
//				prp.load(fis);
//				propertiesResult.putAll(prp);
//				return true;
//			}				
//		} catch (Exception ex) {
////			LOG.error("Can not load property file: " + fileName);
//		} 
//		return false;
//	});
//	return propertiesResult;

//}