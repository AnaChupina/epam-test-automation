package com.epam.tests.demo;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import com.epam.demo.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.epam.demo.SeasonUtils;
import com.epam.demo.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ReportPortalExtension.class)
public class SampleTests {
    @ParameterizedTest
    @ValueSource(ints = {2, 40, 56, 78})
    void isEven (int number) {
        assertTrue(NumberUtils.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"APRIL", "MARCH", "MAY", "april"})
    void isSpringMonth (String input) {
        assertTrue(SeasonUtils.isSpring(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "11", "356965", "984000"})
    void isThisStringNumber (String input) {
        assertTrue(StringUtils.isNumber(input));
    }
    @ParameterizedTest
    @CsvSource({"5,25", "4,20", "8,40"})
    void testMultiplyByFiveFunctionality (int input, int expected) {
        int actualValue = NumberUtils.multiplyByFive(input);
        assertEquals(expected, actualValue);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void getNumberOfDaysInMonth(String input, int expected) {
        int actualValue = SeasonUtils.getNumberOfDaysInMonth(input);
        assertEquals(expected, actualValue);
    }
    @Test
    void checkMethodCompareArraysOfString (){
        String[] first = {"a","b","c"};
        String[] second = {"b","a","c"};
        assertTrue(StringUtils.areArraysEqual(first,second));
    }
}
