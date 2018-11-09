package com.haulmont.testtask.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class LoggerConfigManager extends AbstractConfig {

	private static final String LOGGING_FILE_PROPERTIES = "logging.properties";

	public LoggerConfigManager() throws IOException {

		try (InputStream stream = new FileInputStream(CONFIG_FILE_DIRECTORY + "/" + LOGGING_FILE_PROPERTIES);) {
			LogManager.getLogManager().readConfiguration(stream);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Not load file " + LOGGING_FILE_PROPERTIES, e);
			throw e;
		}
	}
}
