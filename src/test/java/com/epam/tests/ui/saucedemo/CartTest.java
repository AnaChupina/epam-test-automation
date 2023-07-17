package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CartItem;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.CartActions;
import com.epam.ui.services.saucedemo.InventoryActions;
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
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .goToShoppingCart();
        assertTrue(actions.getCartPage().isItCartPage());
    }
    @ParameterizedTest(name = "Item 'Sauce Labs Backpack' is added to cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testIsProductAddedToCart (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Backpack")
                .goToShoppingCart();
        ArrayList<CartItem> cartItems = actions.getAllCartItems();
        assertTrue(cartItems.stream().anyMatch((item) -> item.getName().equals("Sauce Labs Backpack")));
    }
    @ParameterizedTest(name = "Remove item 'Sauce Labs Bike Light' from cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testRemoveButton (String username, String password) {
        User testUser = new User(username, password);
        CartActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .addItemToCart("Sauce Labs Bike Light")
                .removeItemFromCart("Sauce Labs Bike Light")
                .goToShoppingCart();
        ArrayList<CartItem> cartItems = actions.getAllCartItems();
        assertFalse(cartItems.stream().anyMatch((item) -> item.getName().equals("Sauce Labs Bike Light")));

    }
    @ParameterizedTest(name = "Check continue shopping button on cart page")
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
}
