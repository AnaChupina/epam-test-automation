package com.epam.pages;

import com.epam.model.User;
import com.epam.utils.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.epam.waits.ExplicitWait.waitForPresenceOfElement;

public class SauceDemoLoginPage {
    private static final String URL = "https://www.saucedemo.com";
    private final WebDriver driver;
    @FindBy (xpath="//div[@class=\"login_logo\"]")
    private WebElement loginLogo;
    @FindBy (xpath = "//input[@id=\"user-name\"]")
    private WebElement usernameField;
    @FindBy (id = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement loggingErrorMessage;

    public SauceDemoLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public SauceDemoLoginPage openPage() {
        driver.get(URL);
        waitForPresenceOfElement(driver, "//div[@class=\"login_logo\"]");
        return this;
    }
    public SauceDemoInventoryPage login(User user){
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
        return new SauceDemoInventoryPage(driver);
    }
    public String loginWithRandomCredentials() {
        usernameField.sendKeys(Strings.getRandomString());
        passwordField.sendKeys(Strings.getRandomString());
        loginButton.click();
        waitForPresenceOfElement(driver, "//button[@class=\"error-button\"]");
        return loggingErrorMessage.getText();
    }
}
