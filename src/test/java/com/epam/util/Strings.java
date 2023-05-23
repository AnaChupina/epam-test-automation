package com.epam.util;

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
}
