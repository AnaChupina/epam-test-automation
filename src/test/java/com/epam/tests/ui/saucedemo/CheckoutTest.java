package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.CheckoutActions;
import com.epam.ui.services.saucedemo.CheckoutCompleteActions;
import com.epam.ui.services.saucedemo.CheckoutOverviewActions;
import com.epam.ui.services.saucedemo.InventoryActions;
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
        CheckoutActions page = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .goToShoppingCart()
                .goToCheckout();
        assertEquals(CHECKOUT_STEP_ONE_PAGE_URL, driver.getCurrentUrl());
    }
    @ParameterizedTest(name = "Verify correct error message after checkout with empty first name")
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
    @ParameterizedTest(name = "Verify correct error message after checkout with empty last name")
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
    @ParameterizedTest(name = "Verify correct error message after checkout with empty zipcode")
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
    @ParameterizedTest(name = "Check cancel button on checkout step one page")
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
    @ParameterizedTest(name = "Verify correct success message after completing checkout")
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
}
