package com.epam.demo;

import java.util.function.Predicate;

public class NumberUtils {

    public static boolean isEven(int number) {
        Predicate <Integer> lambda_exp = (n) -> (n % 2) == 0;
        return lambda_exp.test(number);
    }
    public static int multiplyByFive (int number) {
        return number*5;
    }
}
