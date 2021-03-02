package com.github.turchev.carrepairshop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PropertiesFactory {

	private static final Logger LOG = LogManager.getLogger();
	private static final Map<String, Properties> propHashMap = new HashMap<>();
	private static final String CONFIG_DIRECTORY = "etc";
	private static boolean configured = false;

	private PropertiesFactory() {
	}

	public Map<String, Properties> getPropHashMap() {
		return propHashMap;
	}

	public Properties getPropertiesByKey(String shortFileName) {
		return propHashMap.get(shortFileName);
	}

	public static PropertiesFactory getInstans() throws ConfigException {

		if (!configured) {
			try (Stream<Path> pathStream = Files.walk(Paths.get(CONFIG_DIRECTORY))) {
				pathStream
						.filter(Files::isRegularFile)
						.filter((filePath) -> filePath.toString().endsWith(".properties") || filePath.toString().endsWith(".xml"))
						.forEach((filePath) -> {
							LOG.debug("Found file: {}", filePath.toString());
							try {
								FileInputStream fis = new FileInputStream(filePath.toString());
								Properties prop = new Properties();
								if (filePath.toString().endsWith(".properties")){
									prop.load(fis);
									propHashMap.put(filePath.getFileName().toString(), prop);
								}
								if (filePath.toString().endsWith(".xml")) {
									prop.loadFromXML(fis);
									propHashMap.put(filePath.getFileName().toString(), prop);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						});

			} catch (IOException e) {
				propHashMap.clear();
				configured = false;
				throw new ConfigException(e);
			}

//			 try {
//				File configDir = new File(CONFIG_DIRECTORY);
//				String[] files = configDir.list();
//				for (String fileName : files) {
//					LOG.debug("Found file: {}", fileName);
//					try (FileInputStream fis = new FileInputStream(CONFIG_DIRECTORY + "/" + fileName)) {
//						Properties prop = new Properties();
//						fileName = fileName.toLowerCase();
//						if (fileName.endsWith(".properties")) {
//							prop.load(fis);
//							propHashMap.put(fileName, prop);
//							continue;
//						}
//						if (fileName.endsWith(".xml")) {
//							prop.loadFromXML(fis);
//							propHashMap.put(fileName, prop);
//						}
//					}
//				}
//			} catch (Exception e) {
//				propHashMap.clear();
//				configured = false;
//				throw new ConfigException(e);
//			}
			configured = true;
		}
		return SingletonHandler.INSTANS;
	}

	private static final class SingletonHandler {
		private static final PropertiesFactory INSTANS = new PropertiesFactory();
	}
}
