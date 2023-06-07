package com.epam.tests.saucedemo;

import com.epam.driver.DriverSingleton;
import com.epam.service.ScreenShootOnFailureExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenShootOnFailureExtension.class)
public class CommonConditions {
    protected WebDriver driver;
    @BeforeEach
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
    }

    @AfterEach
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }
}
