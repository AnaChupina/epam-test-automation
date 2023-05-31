package com.epam.tests.saucedemo;

import com.epam.model.User;
import com.epam.pages.SauceDemoLoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SmokeTests extends CommonConditions{

    @ParameterizedTest(name = "test_1")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void loginTest_RightCredentials (String username, String password, String firstName, String lastName,
                                            String zipCode){
        User testUser = new User(username,password,firstName,lastName,zipCode);
        SauceDemoLoginPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser);
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("test_2")
    public void loginTest_InvalidCredentials (){
        SauceDemoLoginPage page = new SauceDemoLoginPage(driver)
                .openPage();
        String actualErrorMessage = page.loginWithRandomCredentials();
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                actualErrorMessage);
    }
    @Test
    @DisplayName("test_3")
    public void sortingFunctionalityTest_PriceLowToHigh (){

    }
}
