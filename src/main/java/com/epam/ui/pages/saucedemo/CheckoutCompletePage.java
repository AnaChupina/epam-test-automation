package com.epam.ui.pages.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(CartPage.class);
    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement completeMessage;
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public String getCompleteMessage(){
        LOGGER.debug("Complete message:");
        //is it okay to do like that? Put "completeMessage.getText()" into logging
        LOGGER.debug(completeMessage.getText());
        return completeMessage.getText();
    }
}
