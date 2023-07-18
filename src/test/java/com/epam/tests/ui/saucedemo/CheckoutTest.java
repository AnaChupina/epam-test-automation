package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.*;
import com.epam.ui.services.saucedemo.CheckoutActions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseUITest {
    private String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private String CHECKOUT_STEP_ONE_PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    private String ERROR_MESSAGE_FIRST_NAME = "Error: First Name is required";
    private String ERROR_MESSAGE_LAST_NAME = "Error: Last Name is required";
    private String ERROR_MESSAGE_ZIP_CODE = "Error: Postal Code is required";
    private String COMPLETE_ORDER_MESSAGE= "Thank you for your order!";


    @ParameterizedTest(name = "Click checkout button and verify moving to correct webpage")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void TestCheckoutButton (String username, String password) {
        User testUser = new User(username, password);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        assertEquals(CHECKOUT_STEP_ONE_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "Verify correct error message after checkout with empty first name")
    @CsvFileSource(resources = "/checkoutDataEmptyFirstName.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyFirstName  (String username, String password, String firstName, String lastName,
                                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutActions(page)
                .fillOutDeliveryInformation(testUser);
        CheckoutPage newPage = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_FIRST_NAME, page.getErrorMessage());
    }
    @ParameterizedTest(name = "Verify correct error message after checkout with empty last name")
    @CsvFileSource(resources = "/checkoutDataEmptyLastName.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyLastName  (String username, String password, String firstName, String lastName,
                                                       String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutActions(page)
                .fillOutDeliveryInformation(testUser);
        CheckoutPage newPage = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_LAST_NAME, page.getErrorMessage());
    }
    @ParameterizedTest(name = "Verify correct error message after checkout with empty zipcode")
    @CsvFileSource(resources = "/checkoutDataEmptyZipCode.csv", numLinesToSkip = 1)
    public void testCheckoutProcessWithEmptyZipCode  (String username, String password, String firstName, String lastName,
                                                      String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutActions(page)
                .fillOutDeliveryInformation(testUser);
        CheckoutPage newPage = new CheckoutPage(driver);
        assertEquals(ERROR_MESSAGE_ZIP_CODE, page.getErrorMessage());
    }
    @ParameterizedTest(name = "Check cancel button on checkout step one page")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void testCancelButtonFromCheckoutOverviewPage  (String username, String password, String firstName, String lastName,
                                                           String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        InventoryPage newPage = new CheckoutActions(page)
                .fillOutDeliveryInformation(testUser)
                .clickCancelButton();
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "Verify correct success message after completing checkout")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void checkMessageAfterCompletingOrder  (String username, String password, String firstName, String lastName,
                                                   String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        CheckoutPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart()
                .clickShoppingCartLink()
                .clickCheckoutButton();
        CheckoutCompletePage newPage = new CheckoutActions(page)
                .fillOutDeliveryInformation(testUser)
                .clickFinishButton();
        assertEquals(COMPLETE_ORDER_MESSAGE, newPage.getCompleteMessage());
    }
}
