package com.epam.tests.ui.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.utils.logging.ScreenShootOnFailureExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenShootOnFailureExtension.class)
public class BaseTest {
    protected WebDriver driver;
    @BeforeEach
    public void setUp()
    {
        driver = DriverSingleton.getInstance().getDriver();
    }

    @AfterEach
    public void stopBrowser()
    {
        DriverSingleton.getInstance().closeDriver();
    }
}
