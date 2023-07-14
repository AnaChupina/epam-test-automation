package com.epam.api.utils;

import java.io.*;
import java.util.Properties;

public class FileHandler {
    private static Properties properties;

    private static Properties getProperties(String propertiesFileName){
        try {
            FileInputStream file = new FileInputStream("src/test/resources/" + propertiesFileName);
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Sorry, unable to find api.properties");
        }
        return properties;
    }

    public static String getDataFromProperties(String propertiesFileName, String key){
        return getProperties(propertiesFileName).getProperty(key);
    }

}
