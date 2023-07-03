package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.BasePage;
import com.epam.ui.pages.saucedemo.LoginPage;

public class BasicActions {
    private final BasePage page;

    public BasicActions(BasePage page) {
        this.page = page;
    }

    public LoginActions logout(){
        page.clickBurgerMenuButton().clickLogoutButton();
        LoginPage loginPage = new LoginPage(DriverSingleton.getInstance().getDriver());
        return new LoginActions(loginPage);
    }
}
