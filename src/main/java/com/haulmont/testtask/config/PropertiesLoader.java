package com.haulmont.testtask.config;

import java.util.Properties;

public final class PropertiesLoader {

//    static final Logger LOGGER = LogManager.getLogger(PropertiesLoader.class.getName());   
    private static final String CONFIG_FILE_DIRECTORY = "etc";
    private Properties propertiesResult;
    
    private PropertiesLoader() {
    }

    public static PropertiesLoader getInstance() {
        return SingletonHandler.INSTANCE;
    }

    private static class SingletonHandler {
        private static PropertiesLoader INSTANCE = new PropertiesLoader();
    }

    public Properties getProperties() {        
        if(propertiesResult != null) return this.propertiesResult; 
        propertiesResult = PropertiesHelper.loadProperties(CONFIG_FILE_DIRECTORY);  
        return propertiesResult;
    }
}
