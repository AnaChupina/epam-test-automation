package com.epam.ui.pages.saucedemo;

import com.epam.ui.model.User;
import com.epam.ui.services.saucedemo.LoginActions;
import com.epam.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

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
    public LoginActions openPage() {
        driver.get(URL);
        waitForPresenceOfElement(driver, "//div[@class=\"login_logo\"]");
        return new LoginActions(this);
//        return new LoginActions(driver);
    }
    public void inputUsername(User user){
        usernameField.sendKeys(user.getUsername());
    }
    public void inputPassword(User user){
        passwordField.sendKeys(user.getPassword());
    }
    public  void clickLoginButton(){
        loginButton.click();
    }
//    public InventoryPage login(User user){
//        usernameField.sendKeys(user.getUsername());
//        passwordField.sendKeys(user.getPassword());
//        loginButton.click();
//        return new InventoryPage(driver);
//    }
    public void inputRandomUsername(){
        usernameField.sendKeys(StringUtils.getRandomString());
    }
    public void inputRandomPassword(){
        passwordField.sendKeys(StringUtils.getRandomString());
    }
    public String getLoggingErrorMessage(){
        return loggingErrorMessage.getText();
    }
//    public String loginWithRandomCredentials() {
//        usernameField.sendKeys(StringUtils.getRandomString());
//        passwordField.sendKeys(StringUtils.getRandomString());
//        loginButton.click();
//        waitForPresenceOfElement(driver, "//button[@class=\"error-button\"]");
//        return loggingErrorMessage.getText();
//    }
}
