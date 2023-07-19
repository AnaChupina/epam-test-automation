package com.epam.ui.pages.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BurgerMenu {
    private static final Logger LOGGER = LogManager.getLogger(BurgerMenu.class);
    private final WebElement element;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public BurgerMenu(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public LoginPage clickLogoutButton(){
        logoutButton.click();
        LOGGER.info("Click logout button");
        return new LoginPage(DriverSingleton.getInstance().getDriver());
    }
}
