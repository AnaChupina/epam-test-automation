package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;

public class BurgerMenu {
    private final WebElement element;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public BurgerMenu(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public void clickLogoutButton(){
        logoutButton.click();
    }
}
