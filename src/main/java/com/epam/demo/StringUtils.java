package com.epam.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    /*
    Calculate the frequency of each word in the given string
    Examples:
        Input: str = “Geeks For Geeks”
        Output:
        For 1
        Geeks 2
        Explanation:
        For occurs 1 time and Geeks occurs 2 times in the given string str.
     */
    public static Map<String, Integer> calculateFrequencyOfWords (String inputString){
        String [] stringArray = inputString.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String str : stringArray){
            if (map.containsKey(str)){
                map.put(str, map.get(str)+1);
            } else {
                map.put(str, 1);
            }
        }
//        for (var entry : map.entrySet()){
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        return map;
    }
}
