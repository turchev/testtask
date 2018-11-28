package com.haulmont.testtask.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public final class ConfigFactory extends HashMap<String, Properties> {

	private static final Logger LOG = LogManager.getLogger();

	private static final String CONFIG_DIRECTORY = "etc"; // Директория для конфигурационных файлов
	private boolean configured = false; // Флаг чтения конфигурационных файлов

	private ConfigFactory() {
		if (configured == false)
			readСonfigurationt();
	}

	public static ConfigFactory getInstans() {
		return ConfigHandler.INSTANS;
	}

	private static final class ConfigHandler {
		private static ConfigFactory INSTANS = new ConfigFactory();
	}

	private void readСonfigurationt() {

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
						this.put((fileName.substring(0, fileName.length() - 11)), prop);
						continue;
					}
					if (fileName.endsWith(".xml")) {
						prop.loadFromXML(fis);
						this.put((fileName.substring(0, fileName.length() - 4)), prop);
						continue;
					}
				}
			}
		} catch (Exception e) {
			this.clear();
			this.configured = false;
			LOG.warn("Reading configuration failed ", e);
			return;
		}

		this.configured = true;
	}

	public boolean isConfigured() {
		return configured;
	}

	public Properties getPropertiesByFileName(String fileName) {
		return this.get(fileName);
	}

	public String getPropertyByKey(String key) {
		return null;
	}
}
