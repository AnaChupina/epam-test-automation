package com.epam.tests.logging;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.epam.service.ScreenShootOnFailureExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test with extension which makes screenshots with Selenide on test failure.
 */
@ExtendWith(ScreenShootOnFailureExtension.class)
public class SelenideScreenshotLoggingTest {
    @BeforeAll
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = Browsers.CHROME;
    }

    @Test
    public void testTitle() {
        Selenide.open("https://www.example.com");
        assertEquals("Google", Selenide.title());
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
