package com.epam.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

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
    /*
    Check if a given string is a palindrome:
        Write a Java method that checks if a given string is a palindrome (a word, phrase, or sequence that reads the same backward as forward).
        Example:
        Input: "racecar"
        Output: true
     */
    public static Boolean isItPalindrome(String inputString){
        int startIndex = 0;
        int endIndex = inputString.length()-1;
        while(startIndex < endIndex){
            if(inputString.charAt(startIndex) == inputString.charAt(endIndex)){
                startIndex ++;
                endIndex--;
            } else{
                return false;
            }
        }
        return true;
    }
    /*
    Распечатать все числа от 1 до 100, которые делятся на 5 без остатка. Используем цикл do-while.
     */
    public static String getAllNumbersDivisibleBy5(int[] numbers){
        List<Integer> listOfCorrectNumbers = new LinkedList<>();
        int counter = 0;
        do {
            if (NumberUtils.isNumberDivisibleByFive(numbers[counter])){
                listOfCorrectNumbers.add(numbers[counter]);
            }
            counter++;
        }while (counter < numbers.length);
        return listOfCorrectNumbers.toString();
    }
}
