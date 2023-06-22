package com.epam.pages.saucedemo;

import com.epam.model.User;
import com.epam.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.epam.waits.ExplicitWait.waitForPresenceOfElement;

public class LoginPage {
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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public LoginPage openPage() {
        driver.get(URL);
        waitForPresenceOfElement(driver, "//div[@class=\"login_logo\"]");
        return this;
    }
    public InventoryPage login(User user){
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
        return new InventoryPage(driver);
    }
    public String loginWithRandomCredentials() {
        usernameField.sendKeys(StringUtils.getRandomString());
        passwordField.sendKeys(StringUtils.getRandomString());
        loginButton.click();
        waitForPresenceOfElement(driver, "//button[@class=\"error-button\"]");
        return loggingErrorMessage.getText();
    }
}
