package com.epam.demo;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class NumberUtils {

    public static boolean isEven(int number) {
        Predicate <Integer> lambda_exp = (n) -> (n % 2) == 0;
        return lambda_exp.test(number);
    }
    public static int multiplyByFive (int number) {
        return number*5;
    }

    /*
    Распечатать все числа от 1 до 100, которые делятся на 5 без остатка. Используем цикл do-while.
     */
    public static boolean isNumberDivisibleByFive (int number){
        Predicate <Integer> lambda_exp = (n) -> (n % 5) == 0;
        return lambda_exp.test(number);
    }

    public static int[] getArrayOfNumbersFromOneToN(int n){
        return IntStream.rangeClosed(1, n).toArray();
    }
}
