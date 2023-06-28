package com.epam.tests.demo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportsStepWithDefectTest {
    private static final Logger LOGGER = LogManager.getLogger(ReportsStepWithDefectTest.class);

    @Test
    @Disabled("for demonstration purposes")
    @DisplayName("DisabledTest")
    public void testSkipped() {
        LOGGER.info("I'm disabled test");
    }

    @Test
    public void testFailure() {
        assertEquals(2, 1);
    }

    @Test
    public void testFailureWithCustomMessage() {
        assertEquals(2, 1, "Failure msg");
    }

    @Test
    public void expectedFailureAbsent() {
        assertThrows(AssertionError.class, () -> assertEquals(1, 1));
    }
}
