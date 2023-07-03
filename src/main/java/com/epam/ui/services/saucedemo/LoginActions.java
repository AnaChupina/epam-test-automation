package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.InventoryPage;
import com.epam.ui.pages.saucedemo.LoginPage;
import org.openqa.selenium.WebDriver;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class LoginActions {
//    private final WebDriver driver;
    private final LoginPage page;

    public LoginActions(LoginPage page) {
        this.page = page;
//        this.driver = driver;
    }

    public InventoryActions login(User user){
//        LoginPage page = new LoginPage(driver);
        page.inputUsername(user);
        page.inputPassword(user);
        page.clickLoginButton();
        // check if login is successful
        //if not - throw an exception
        InventoryPage inventoryPage = new InventoryPage(DriverSingleton.getInstance().getDriver());
        return new InventoryActions(inventoryPage);
    }
    public String loginWithRandomCredentials() {
//        LoginPage page = new LoginPage(driver);
        page.inputRandomUsername();
        page.inputRandomPassword();
        page.clickLoginButton();
        waitForPresenceOfElement(DriverSingleton.getInstance().getDriver(), "//button[@class=\"error-button\"]");
        return page.getLoggingErrorMessage();
    }
}
