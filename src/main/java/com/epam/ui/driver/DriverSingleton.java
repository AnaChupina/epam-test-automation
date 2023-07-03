package com.epam.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverSingleton {
    private static DriverSingleton instance = null;
    private  WebDriver driver ;
    private DriverSingleton(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstance() {
        if (instance == null) {
            instance = new DriverSingleton();
        }
        return instance;
    }
    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver(){
        driver.quit();
        instance = null;
    }
}

