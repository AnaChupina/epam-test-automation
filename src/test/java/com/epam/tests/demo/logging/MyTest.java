package com.epam.tests.demo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class MyTest {
    private static final Logger LOGGER = LogManager.getLogger(MyTest.class);

    @Test
    void testMySimpleTest() {
        LOGGER.info("Hello from my simple test");
    }
}
