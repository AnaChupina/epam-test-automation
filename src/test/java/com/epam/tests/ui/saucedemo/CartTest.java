package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseUITest {
    private String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @ParameterizedTest(name = "Test cart button on inventory page")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testCartButton (String username, String password){
        User testUser = new User(username,password);
        CartPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickShoppingCartLink();
        assertTrue(page.isItCartPage());
    }
    @ParameterizedTest(name = "Item 'Sauce Labs Backpack' is added to cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testIsProductAddedToCart (String username, String password) {
        User testUser = new User(username, password);
        CartPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Backpack")
                .clickAddToCart()
                .clickShoppingCartLink();
        ArrayList<CartItem> cartItems = page.getAllItemsInCart();
        assertTrue(cartItems.stream().anyMatch((cartItem) -> cartItem.getName().equals("Sauce Labs Backpack")));
    }
    @ParameterizedTest(name = "Remove item 'Sauce Labs Bike Light' from cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testRemoveButton (String username, String password) {
        User testUser = new User(username, password);
        InventoryItem item = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .findItem("Sauce Labs Bike Light")
                .clickAddToCart();
        CartPage page = new InventoryPage(driver)
                .findItem("Sauce Labs Bike Light")
                .clickRemoveFromCart()
                .clickShoppingCartLink();
        ArrayList<CartItem> cartItems = page.getAllItemsInCart();
        assertFalse(cartItems.stream().anyMatch((cartItem) -> cartItem.getName().equals("Sauce Labs Bike Light")));
    }
    @ParameterizedTest(name = "Check continue shopping button on cart page")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testContinueShoppingButton (String username, String password) {
        User testUser = new User(username, password);
        CartPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickShoppingCartLink()
                .clickContinueShoppingButton();
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
}
