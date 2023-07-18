package com.epam.tests.ui.saucedemo;

import com.epam.tests.base.BaseUITest;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutTest extends BaseUITest {
    private String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    @ParameterizedTest(name = "Logout from inventory page")
    @CsvFileSource(resources = "/loginData.csv", numLinesToSkip = 1)
    public void testLogoutProcess  (String username, String password) {
        User testUser = new User(username, password);
        LoginPage page = new LoginPage(driver)
                .openPage()
                .inputUsername(testUser)
                .inputPassword(testUser)
                .clickLoginButton()
                .clickBurgerMenuButton()
                .clickLogoutButton();
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
