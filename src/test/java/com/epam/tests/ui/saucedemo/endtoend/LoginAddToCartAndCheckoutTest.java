package com.epam.tests.ui.saucedemo.endtoend;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import com.epam.ui.pages.saucedemo.InventoryPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.CheckoutActions;
import com.epam.ui.services.saucedemo.InventoryActions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAddToCartAndCheckoutTest extends BaseUITest {
    private String EXPECTED_TOTAL_WITH_TAX = "Total: $140.34";

    @ParameterizedTest(name = "Login, add 6 items to cart, checkout, verify total price with tax")
    @CsvFileSource(resources = "/correctCheckoutData.csv", numLinesToSkip = 1)
    public void checkTotalPriceWithTax (String username, String password, String firstName, String lastName,
                                        String zipCode) {
        User testUser = new User(username, password, firstName, lastName, zipCode);
        InventoryPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton();
        InventoryActions actions = new InventoryActions(page)
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .addItemToCart("Sauce Labs Fleece Jacket")
                .addItemToCart("Sauce Labs Onesie")
                .addItemToCart("Test.allTheThings() T-Shirt (Red)");
        page.clickShoppingCartLink()
                .clickCheckoutButton();
        CheckoutOverviewPage newPage = new CheckoutActions(new CheckoutPage(driver))
                .fillOutDeliveryInformation(testUser);
        assertEquals(EXPECTED_TOTAL_WITH_TAX, newPage.getTotalPriceWithTax());
    }
}
