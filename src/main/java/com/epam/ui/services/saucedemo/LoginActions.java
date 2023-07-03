package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.InventoryPage;
import com.epam.ui.pages.saucedemo.LoginPage;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class LoginActions {
    private final LoginPage page;

    public LoginActions(LoginPage page) {
        this.page = page;
    }

    public InventoryActions login(User user){
        page.inputUsername(user);
        page.inputPassword(user);
        page.clickLoginButton();
        InventoryPage inventoryPage = new InventoryPage(DriverSingleton.getInstance().getDriver());
        return new InventoryActions(inventoryPage);
    }
    public String loginWithRandomCredentials() {
        page.inputRandomUsername();
        page.inputRandomPassword();
        page.clickLoginButton();
        waitForPresenceOfElement(DriverSingleton.getInstance().getDriver(), "//button[@class=\"error-button\"]");
        return page.getLoggingErrorMessage();
    }

}
