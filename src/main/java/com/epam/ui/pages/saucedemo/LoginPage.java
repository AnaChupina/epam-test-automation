package com.epam.ui.pages.saucedemo;

import com.epam.ui.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class LoginPage {
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);
    private static final String URL = "https://www.saucedemo.com";
    private final WebDriver driver;
    @FindBy (xpath="//div[@class=\"login_logo\"]")
    private WebElement loginLogo;
    @FindBy (xpath = "//input[@id=\"user-name\"]")
    private WebElement usernameField;
    @FindBy (xpath = "//input[@id='password']")
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
        LOGGER.info("Page \"https://www.saucedemo.com\" was opened");
        return this;
    }
    public LoginPage inputUsername(User user){
        usernameField.sendKeys(user.getUsername());
        LOGGER.debug(String.format("Input username = %s", user.getUsername()));
        return this;
    }
    public LoginPage inputUsername(String username){
        usernameField.sendKeys(username);
        LOGGER.debug(String.format("Input username = %s", username));
        return this;
    }
    public LoginPage inputPassword(User user){
        passwordField.sendKeys(user.getPassword());
        LOGGER.debug(String.format("Input password = %s", user.getPassword()));
        return this;
    }
    public LoginPage inputPassword(String password){
        passwordField.sendKeys(password);
        LOGGER.debug(String.format("Input password = %s", password));
        return this;
    }
    public  InventoryPage clickLoginButton(){
        loginButton.click();
        LOGGER.info("Click login button");
        return new InventoryPage(driver);
    }
    public String getLoggingErrorMessage(){
        loginButton.click();
        LOGGER.debug(loggingErrorMessage.getText());
        return loggingErrorMessage.getText();
    }
}
