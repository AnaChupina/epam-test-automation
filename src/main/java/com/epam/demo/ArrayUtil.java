package com.epam.demo;

import java.util.*;
import java.lang.String;

public class ArrayUtil {
    /*Remove duplicates from an ArrayList:
    Write a Java method that removes duplicates from an ArrayList of integers and returns the modified ArrayList.
            Example:
    Input: [1, 2, 3, 3, 4, 5, 5]
    Output: [1, 2, 3, 4, 5]

     */
    public static <T> ArrayList<T> removeDuplicates(List<T> list) {
        LinkedHashSet<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }
}
