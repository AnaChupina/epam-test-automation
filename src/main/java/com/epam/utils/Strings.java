package com.epam.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Strings {

    public static boolean isNumber (String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        }catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
    public static String getRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
    public static boolean compareArraysOfString_AreArraysEqual(String[] firstArray, String[] secondArray){
        int equalStringCounter = 0;
        if(firstArray.length != secondArray.length){
            return false;
        }
        for (String value : firstArray) {
            for (String str : secondArray) {
                if (value.equals(str)) {
                    equalStringCounter++;
                }
            }
        }
        return equalStringCounter == firstArray.length;
    }
}
