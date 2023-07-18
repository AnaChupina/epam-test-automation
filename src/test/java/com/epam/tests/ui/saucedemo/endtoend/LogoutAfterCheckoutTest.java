package com.epam.tests.ui.saucedemo.endtoend;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.*;
import com.epam.ui.services.saucedemo.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.*;

public class LogoutAfterCheckoutTest extends BaseUITest {
    private String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    @ParameterizedTest(name = "Login, add 6 items to cart, checkout, logout")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void logoutAfterCheckoutTest (String username, String password, String firstName, String lastName,
                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton();
        InventoryAction actions = new InventoryAction(page)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)");
        page.clickShoppingCartLink()
                .clickCheckoutButton();
        LoginPage newPage = new CheckoutAction(new CheckoutPage(driver))
                .fillOutDeliveryInformation(testUser)
                .clickFinishButton()
                .clickBurgerMenuButton()
                .clickLogoutButton();
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
