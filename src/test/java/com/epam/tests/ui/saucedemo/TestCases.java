package com.epam.tests.ui.saucedemo;

import com.epam.tests.ui.saucedemo.testdata.TestData;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.InventoryPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.CartActions;
import com.epam.ui.services.saucedemo.CheckoutOverviewActions;
import com.epam.ui.services.saucedemo.LoginActions;
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
        CheckoutOverviewActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser);
        assertEquals(EXPECTED_TOTAL_WITH_TAX, actions.getTotalPriceWithTax());
    }
    @ParameterizedTest(name = "e2e_2")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkNumberOfItemsInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .logout()
                .login(testUser)
                .goToShoppingCart();
        assertEquals(EXPECTED_NUMBER_OF_ITEMS, actions.getNumberOfItemsInCart());
    }
    @ParameterizedTest(name = "e2e_3")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void logoutAfterCheckoutTest (String username, String password, String firstName, String lastName,
                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        LoginActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser)
                .finishCheckout()
                .logout();
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "e2e_4")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkWhatItemsAreInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)")
                .logout()
                .login(testUser)
                .goToShoppingCart();
        assertTrue(StringUtils.areArraysEqual(TestData.getArrayOfAllItemNames(),
               actions.getNamesOfItemsInCart(actions.getNumberOfItemsInCart())));
    }
}
