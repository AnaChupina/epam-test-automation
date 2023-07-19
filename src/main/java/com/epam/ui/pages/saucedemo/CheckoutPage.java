package com.epam.ui.pages.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;

public class CheckoutPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(CheckoutPage.class);
    @FindBy(xpath="//input[@id='first-name']")
    private WebElement firstNameField;
    @FindBy(xpath="//input[@id='last-name']")
    private WebElement lastNameField;
    @FindBy(xpath="//input[@id='postal-code']")
    private WebElement zipCode;
    @FindBy(xpath="//input[@id='continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement errorMessageFromCheckoutProcess;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public void inputFirstName(String name){
        waitForClickabilityOfElement(driver,firstNameField);
        firstNameField.sendKeys(name);
    }
    public void inputLastName(String name){
        waitForClickabilityOfElement(driver,lastNameField);
        lastNameField.sendKeys(name);
    }
    public void inputZipCode(String code){
        waitForClickabilityOfElement(driver,zipCode);
        zipCode.sendKeys(code);
    }
    public void clickContinueButton(){
        continueButton.click();
    }
    public String getErrorMessage(){
        LOGGER.trace(errorMessageFromCheckoutProcess.getText());
        return errorMessageFromCheckoutProcess.getText();
    }

}
