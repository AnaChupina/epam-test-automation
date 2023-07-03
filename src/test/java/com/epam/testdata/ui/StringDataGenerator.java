package com.epam.testdata.ui;

import org.apache.commons.lang3.RandomStringUtils;

public class StringDataGenerator {
    public static String getRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
