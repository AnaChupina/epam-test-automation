package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage{
    @FindBy(className = "complete-header")
    private WebElement completeMessage;
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public String getCompleteMessage(){
        return completeMessage.getText();
    }
}
