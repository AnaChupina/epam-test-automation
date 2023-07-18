package com.epam.tests.ui.saucedemo.endtoend;

import com.epam.testdata.ui.sausedemo.TestData;
import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CartItem;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.InventoryPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.InventoryAction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest extends BaseUITest {
    private int EXPECTED_NUMBER_OF_ITEMS = 6;

    @ParameterizedTest(name = "Login, add 6 items to the cart, logout, login, verify that it is 6 items in the cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkNumberOfItemsInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton();
        InventoryAction action = new InventoryAction(page)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)");
        page.clickBurgerMenuButton()
                .clickLogoutButton();
        CartPage newPage = new LoginPage(driver)
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickShoppingCartLink();
        assertEquals(EXPECTED_NUMBER_OF_ITEMS, newPage.getAllItemsInCart().size());
    }

    @ParameterizedTest(name = "Login, add 6 items to the cart, logout, login, check what is in the cart")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void checkWhatItemsAreInCartAfterLogout (String username, String password) {
        User testUser = new User(username, password);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton();
        InventoryAction action = new InventoryAction(page)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)");

        page.clickBurgerMenuButton()
                .clickLogoutButton();

        CartPage newPage = new LoginPage(driver)
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickShoppingCartLink();

        String[] allNames = newPage
                .getAllItemsInCart()
                .stream()
                .map(CartItem::getName)
                .sorted()
                .toArray(String[]::new);

        String[] expected = TestData.getArrayOfAllItemNames();
        Arrays.sort(expected);
        assertArrayEquals(expected, allNames);
    }
}
