package com.epam.tests.ui.saucedemo;

import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class SmokeTests extends BaseTest {
    private String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private String ERROR_MESSAGE_LOGIN_WITH_INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service";
    private String CHECKOUT_STEP_ONE_PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    private String ERROR_MESSAGE_FIRST_NAME = "Error: First Name is required";
    private String ERROR_MESSAGE_LAST_NAME = "Error: Last Name is required";
    private String ERROR_MESSAGE_ZIP_CODE = "Error: Postal Code is required";
    private String COMPLETE_ORDER_MESSAGE= "Thank you for your order!";
    private String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    @ParameterizedTest(name = "test_1")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testLoginWithRightCredentials (String username, String password) {
        User testUser = new User(username,password);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser);
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("test_2")
    public void testLoginWithInvalidCredentials (){
        LoginActions actions = new LoginPage(driver)
                .openPage();
//        User randomUser = new User(StringDataGenerator.getRandomString(), StringDataGenerator.getRandomString());
//        actions.login(randomUser);
        //TODO:replace loginWithRandomCredentials with login(random user)
        String actualErrorMessage = actions.loginWithRandomCredentials();
        assertEquals(ERROR_MESSAGE_LOGIN_WITH_INVALID_CREDENTIALS,
                actualErrorMessage);
    }
    @ParameterizedTest(name = "test_3")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testSortingFunctionalityPriceLowToHigh (String username, String password){
        User testUser = new User(username,password);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser);
        String actualNameOfFirstItem = actions.sortItems("low to high");
        assertEquals("Sauce Labs Onesie", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "test_4")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testSortingFunctionalityPriceHighToLow (String username, String password){
        User testUser = new User(username,password);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser);
        String actualNameOfFirstItem = actions.sortItems("high to low");
        assertEquals("Sauce Labs Fleece Jacket", actualNameOfFirstItem);
    }
    @ParameterizedTest(name = "test_5")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testProductPrice (String username, String password){
        User testUser = new User(username,password);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser);
        String actualProductPrice = actions.getProductPrice("Sauce Labs Fleece Jacket");
        assertEquals("$49.99", actualProductPrice);
    }
    @ParameterizedTest(name = "test_6")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testCartButton (String username, String password){
        User testUser = new User(username,password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .goToShoppingCart();
        assertTrue(actions.getCartPage().isItCartPage());
    }
    @ParameterizedTest(name = "test_7")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testIsProductAddedToCart (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .goToShoppingCart();
        int nbItems = actions.getNumberOfItemsInCart();
        String[] items = actions.getNamesOfItemsInCart(nbItems);
        assertTrue(Arrays.asList(items).contains("Sauce Labs Backpack"));
//        assertTrue(page.isProductInCart("Sauce Labs Backpack"));
    }
    @ParameterizedTest(name = "test_8")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testRemoveButton (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .removeItemFromCart("Sauce Labs Bike Light")
                .goToShoppingCart();
        int nbItems = actions.getNumberOfItemsInCart();
        String[] items = actions.getNamesOfItemsInCart(nbItems);
        assertFalse(Arrays.asList(items).contains("Sauce Labs Bike Light"));
//        assertFalse(page.isProductInCart("Sauce Labs Bike Light"));
    }
    //TODO: ask about using url in asserts
    @ParameterizedTest(name = "test_9")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testContinueShoppingButton (String username, String password) {
        User testUser = new User(username, password);
        InventoryActions page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .goToShoppingCart()
                .continueShopping();
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "test_10")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void TestCheckoutButton (String username, String password) {
        User testUser = new User(username, password);
        CheckoutActions page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .goToShoppingCart()
                .goToCheckout();
        assertEquals(CHECKOUT_STEP_ONE_PAGE_URL, driver.getCurrentUrl());
    }
    //TODO: check test_11. Ask about get error message
    @ParameterizedTest(name = "test_11")
    @CsvFileSource(resources = "/checkoutDataEmptyFirstName.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyFirstName  (String username, String password, String firstName, String lastName,
                                    String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutOverviewActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser);
        CheckoutPage page = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_FIRST_NAME, page.getErrorMessage());
    }
    @ParameterizedTest(name = "test_12")
    @CsvFileSource(resources = "/checkoutDataEmptyLastName.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyLastName  (String username, String password, String firstName, String lastName,
                                                     String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutOverviewActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser);
        CheckoutPage page = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_LAST_NAME, page.getErrorMessage());
    }
    @ParameterizedTest(name = "test_13")
    @CsvFileSource(resources = "/checkoutDataEmptyZipCode.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyZipCode  (String username, String password, String firstName, String lastName,
                                                    String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutOverviewActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser);
        CheckoutPage page = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_ZIP_CODE, page.getErrorMessage());
    }
    @ParameterizedTest(name = "test_14")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void testCancelButtonFromCheckoutOverviewPage  (String username, String password, String firstName, String lastName,
                                                   String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser)
                .cancelCheckout();
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "test_15")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void checkMessageAfterCompletingOrder  (String username, String password, String firstName, String lastName,
                                                           String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutCompleteActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .goToShoppingCart()
                .goToCheckout()
                .fillOutDeliveryInformation(testUser)
                .finishCheckout();
        assertEquals(COMPLETE_ORDER_MESSAGE, actions.getCompleteMessage());
    }
    @ParameterizedTest(name = "test_16")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testLogoutProcess  (String username, String password) {
        User testUser = new User(username, password);
        LoginActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .logout();
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
