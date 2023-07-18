package com.epam.ui.pages.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BurgerMenu {
    private final WebElement element;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public BurgerMenu(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public LoginPage clickLogoutButton(){
        logoutButton.click();
        return new LoginPage(DriverSingleton.getInstance().getDriver());
    }
}
