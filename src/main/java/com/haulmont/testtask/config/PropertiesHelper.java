package com.haulmont.testtask.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesHelper {

//    static final Logger LOGGER = LogManager.getLogger(PropertiesHelper.class.getName());

    public static Properties loadProperties(final String CONFIG_FILE_DIRECTORY) {

        Properties propertiesResult = new Properties();
        File configDir = new File(CONFIG_FILE_DIRECTORY);
        String[] files = null;

        files = configDir.list((File folder, String name) -> {

            String fileName = CONFIG_FILE_DIRECTORY + "/" + name;
            FileInputStream fis = null;
            Properties prp = new Properties();
            try {
                fis = new FileInputStream(fileName); 

                if (name.toLowerCase().endsWith(".xml")) {
                    prp.loadFromXML(fis);
                    propertiesResult.putAll(prp);
                    return true;
                }
                if (name.toLowerCase().endsWith(".properties")) {
                    prp.load(fis);
                    propertiesResult.putAll(prp);
                    return true;
                }
            } catch (FileNotFoundException ex) {
//                LOGGER.error("Property file not found: " + fileName);
            } catch (IOException ex) {
//                LOGGER.error("Can not load property file: " + fileName);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {
//                        LOGGER.error("Can not close the properties file: " + fileName);
                    }
                }
            }
            return false;
        });

        return propertiesResult;
    }
}
