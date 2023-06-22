package com.epam.tests.saucedemo;

import com.epam.tests.saucedemo.testdata.TestData;
import com.epam.model.User;
import com.epam.pages.saucedemo.CartPage;
import com.epam.pages.saucedemo.InventoryPage;
import com.epam.pages.saucedemo.LoginPage;
import com.epam.utils.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCases extends BaseTest {
    private String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private String EXPECTED_TOTAL_WITH_TAX = "Total: $140.34";
    private int EXPECTED_NUMBER_OF_ITEMS = 6;
    @ParameterizedTest(name = "e2e_1")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void checkTotalPriceWithTax (String username, String password, String firstName, String lastName,
                                                   String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)");
        CartPage cartPage = new CartPage(driver)
                .openCart()
                .pushCheckoutButton()
                .checkoutProcess(testUser);
        assertEquals(EXPECTED_TOTAL_WITH_TAX, cartPage.getTotalPriceWithTax());
    }
    @ParameterizedTest(name = "e2e_2")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkNumberOfItemsInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)")
                .logout();
        InventoryPage newPage = new LoginPage(driver)
                .login(testUser)
                .redirectToCart();
        CartPage cartPage = new CartPage(driver);
        assertEquals(EXPECTED_NUMBER_OF_ITEMS, cartPage.getNumberOfItemsInCart());
    }
    @ParameterizedTest(name = "e2e_3")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void logoutAfterCheckoutTest (String username, String password, String firstName, String lastName,
                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)");
        CartPage cartPage = new CartPage(driver)
                .openCart()
                .pushCheckoutButton()
                .checkoutProcess(testUser)
                .completeOrder()
                .logout();
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "e2e_4")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkWhatItemsAreInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addToCart("sauce-labs-backpack")
                .addToCart("sauce-labs-bike-light")
                .addToCart("sauce-labs-bolt-t-shirt")
                .addToCart("sauce-labs-fleece-jacket")
                .addToCart("sauce-labs-onesie")
                .addToCart("test.allthethings()-t-shirt-(red)")
                .logout();
        InventoryPage newPage = new LoginPage(driver)
                .login(testUser)
                .redirectToCart();
        CartPage cartPage = new CartPage(driver);
        assertTrue(StringUtils.areArraysEqual(TestData.getArrayOfAllItemNames(),
                cartPage.getNamesOfItemsInCart(cartPage.getNumberOfItemsInCart())));
    }
}
