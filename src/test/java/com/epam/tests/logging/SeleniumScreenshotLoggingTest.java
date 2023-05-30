package com.epam.tests.logging;

import com.epam.service.ScreenShootOnFailureExtension;
import com.epam.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test with extension which makes screenshots with Selenium on test failure.
 */

@ExtendWith(ScreenShootOnFailureExtension.class)
public class SeleniumScreenshotLoggingTest {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp()
    {
        driver = DriverSingleton.getDriver();
    }


    @Test
    public void testTitle() {
        driver.get("https://www.example.com");
        assertEquals("Google", driver.getTitle());
    }

    @AfterEach
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }

}
