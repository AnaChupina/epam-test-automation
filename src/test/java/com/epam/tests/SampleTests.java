package com.epam.tests;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import com.epam.util.Numbers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.epam.util.Seasons;
import com.epam.util.Strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ReportPortalExtension.class)
public class SampleTests {
    @ParameterizedTest
    @ValueSource(ints = {2, 40, 56, 78})
    void isEven_ShouldReturnTrueForEvenNumbers(int number) {
        assertTrue(Numbers.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"APRIL", "MARCH", "MAY", "april"})
    void isSpring_ShouldReturnTrueForSpringMonths(String input) {
        assertTrue(Seasons.isSpring(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "11", "356965", "984000"})
    void isThisStringNumber_ShouldReturnTrueForNumbers(String input) {
        assertTrue(Strings.isNumber(input));
    }
    @ParameterizedTest
    @CsvSource({"5,25", "4,20", "8,40"})
    void multiplyByFive_ShouldReturnNumberFiveTimesGreater(int input, int expected) {
        int actualValue = Numbers.multiplyByFive(input);
        assertEquals(expected, actualValue);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void getNumberOfDaysInMonth_ShouldReturnHowManyDaysInMonth(String input, int expected) {
        int actualValue = Seasons.getNumberOfDaysInMonth(input);
        assertEquals(expected, actualValue);
    }
}
