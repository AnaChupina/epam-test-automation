package com.epam.api.utils;

import java.io.*;
import java.util.Properties;

public class TestDataReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/api.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Sorry, unable to find api.properties");
        }
    }
    public static String getTestData (String key){
        return properties.getProperty(key);
    }

}
