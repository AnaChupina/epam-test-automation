package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest extends BaseUITest {

    @ParameterizedTest(name = "Sort price low to high and verify result")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testSortingFunctionalityPriceLowToHigh (String username, String password){
        User testUser = new User(username,password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickSortContainerButton()
                .choseSortingOption("low to high");
        String actualNameOfFirstItem = page.getAllItemsOnPage().get(0).getName();
        assertEquals("Sauce Labs Onesie", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "Sort price high to low and verify result")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testSortingFunctionalityPriceHighToLow (String username, String password){
        User testUser = new User(username,password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickSortContainerButton()
                .choseSortingOption("high to low");
        String actualNameOfFirstItem = page.getAllItemsOnPage().get(0).getName();
        assertEquals("Sauce Labs Fleece Jacket", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "Test product price")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testProductPrice (String username, String password){
        User testUser = new User(username,password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickProduct("Sauce Labs Fleece Jacket");
        String actualProductPrice = page.getCurrentProductPrice();
        assertEquals("$49.99", actualProductPrice);
    }
}
