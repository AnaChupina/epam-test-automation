package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.LoginPage;
import com.epam.ui.services.saucedemo.InventoryActions;
import com.epam.ui.services.saucedemo.LoginActions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseUITest {
    private String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private String ERROR_MESSAGE_LOGIN_WITH_INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service";

    @ParameterizedTest(name = "Login with right credentials")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testLoginWithRightCredentials (String username, String password) {
        User testUser = new User(username,password);
        InventoryActions actions = new LoginPage(driver)
                .openPage()
                .login(testUser);
        assertEquals(INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Login with invalid random credentials")
    public void testLoginWithInvalidCredentials (){
        LoginActions actions = new LoginPage(driver)
                .openPage();
        String actualErrorMessage = actions.loginWithRandomCredentials();
        assertEquals(ERROR_MESSAGE_LOGIN_WITH_INVALID_CREDENTIALS,
                actualErrorMessage);
    }
}
