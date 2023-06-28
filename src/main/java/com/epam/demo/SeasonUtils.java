package com.epam.demo;

public class SeasonUtils {
    public static boolean isSpring (String str){
        String string = str.toUpperCase();
        return switch (string) {
            case "MARCH", "APRIL", "MAY" -> true;
            default -> false;
        };
    }
    public static int getNumberOfDaysInMonth (String str) {
        String string = str.toUpperCase();
        return switch (string) {
            case "JANUARY", "MARCH", "MAY", "JULY", "AUGUST", "OCTOBER", "DECEMBER" -> 31;
            case "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER" -> 30;
            case "FEBRUARY" -> 28;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
