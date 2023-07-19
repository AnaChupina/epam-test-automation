package com.epam.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class FileHandler {
    private static final Logger LOGGER = LogManager.getLogger(FileHandler.class);
    private static Properties properties;

    private static Properties getProperties(String propertiesFileName){
        try {
            FileInputStream file = new FileInputStream("src/test/resources/" + propertiesFileName);
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            LOGGER.error(String.format("Sorry, unable to find properties file %s", propertiesFileName), e);
        }
        return properties;
    }

    public static String getDataFromProperties(String propertiesFileName, String key){
        return getProperties(propertiesFileName).getProperty(key);
    }

}
