package com.epam.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

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
        String[] newArray = new String[firstArray.length];
        if(firstArray.length != secondArray.length){
            return false;
        }
        for (int i = 0; i<firstArray.length; i++) {
            for (String str : secondArray) {
                if (firstArray[i].equals(str)) {
                    newArray[i] = str;
                }
            }
        }
        return Arrays.equals(newArray, firstArray);
    }
}
