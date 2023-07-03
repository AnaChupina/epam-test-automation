package com.epam.demo;

import java.util.Arrays;

public class StringUtils {
    public static boolean isNumber (String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        }catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
    public static boolean areArraysEqual(String[] firstArray, String[] secondArray){
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
