package com.epam.tests.saucedemo;

import com.epam.model.User;
import com.epam.pages.SauceDemoInventoryPage;
import com.epam.pages.SauceDemoLoginPage;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;


public class SmokeTests extends CommonConditions{

    @ParameterizedTest(name = "test_1")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void loginTest_RightCredentials (String username, String password, String firstName, String lastName,
                                            String zipCode){
        User testUser = new User(username,password,firstName,lastName,zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
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
    @ParameterizedTest(name = "test_3")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void sortingFunctionalityTest_PriceLowToHigh (String username, String password, String firstName, String lastName,
                                                         String zipCode){
        User testUser = new User(username,password,firstName,lastName,zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser);
        String actualNameOfFirstItem = page.sortItems("low to high");
        assertEquals("Sauce Labs Onesie", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "test_4")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void sortingFunctionalityTest_PriceHighToLow (String username, String password, String firstName, String lastName,
                                                         String zipCode){
        User testUser = new User(username,password,firstName,lastName,zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser);
        String actualNameOfFirstItem = page.sortItems("high to low");
        assertEquals("Sauce Labs Fleece Jacket", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "test_5")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void productPriceTest (String username, String password, String firstName, String lastName,
                                                         String zipCode){
        User testUser = new User(username,password,firstName,lastName,zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser);
        String actualProductPrice = page.getProductPrice("Sauce Labs Fleece Jacket");
        assertEquals("$49.99", actualProductPrice);
    }
    @ParameterizedTest(name = "test_6")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void isProductAddedToCart (String username, String password, String firstName, String lastName,
                                  String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack");
        assertTrue(page.isProductInCart("Sauce Labs Backpack"));
    }
    @ParameterizedTest(name = "test_7")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void RemoveButtonTest (String username, String password, String firstName, String lastName,
                                      String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-bike-light")
                .removeProductFromCart("sauce-labs-bike-light");
        assertFalse(page.isProductInCart("Sauce Labs Bike Light"));
    }

}
