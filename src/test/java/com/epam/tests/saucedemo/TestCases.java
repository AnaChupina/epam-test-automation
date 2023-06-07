package com.epam.tests.saucedemo;

import com.epam.tests.testdata.TestData;
import com.epam.model.User;
import com.epam.pages.SauceDemoCartPage;
import com.epam.pages.SauceDemoInventoryPage;
import com.epam.pages.SauceDemoLoginPage;
import com.epam.utils.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCases extends CommonConditions{
    @ParameterizedTest(name = "e2e_1")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void checkTotalPriceWithTax (String username, String password, String firstName, String lastName,
                                                   String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)");
        SauceDemoCartPage cartPage = new SauceDemoCartPage(driver)
                .openCart()
                .pushCheckoutButton()
                .checkoutProcess(testUser);
        assertEquals("Total: $140.34", cartPage.getTotalPriceWithTax());
    }
    @ParameterizedTest(name = "e2e_2")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkNumberOfItemsInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)")
                .logout();
        SauceDemoInventoryPage newPage = new SauceDemoLoginPage(driver)
                .login(testUser)
                .redirectToCart();
        SauceDemoCartPage cartPage = new SauceDemoCartPage(driver);
        assertEquals(6, cartPage.getNumberOfItemsInCart());
    }
    @ParameterizedTest(name = "e2e_3")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void logoutAfterCheckoutTest (String username, String password, String firstName, String lastName,
                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)");
        SauceDemoCartPage cartPage = new SauceDemoCartPage(driver)
                .openCart()
                .pushCheckoutButton()
                .checkoutProcess(testUser)
                .completeOrder()
                .logout();
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "e2e_4")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkWhatItemsAreInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        SauceDemoInventoryPage page = new SauceDemoLoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)")
                .logout();
        SauceDemoInventoryPage newPage = new SauceDemoLoginPage(driver)
                .login(testUser)
                .redirectToCart();
        SauceDemoCartPage cartPage = new SauceDemoCartPage(driver);
        assertTrue(Strings.compareArraysOfString_AreArraysEqual(TestData.getArrayOfAllItemNames(),
                cartPage.getNamesOfItemsInCart(cartPage.getNumberOfItemsInCart())));
    }
}
