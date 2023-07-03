package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class CheckoutOverviewPage extends BasePage{
    @FindBy(xpath="//button[@id='cancel']")
    private WebElement cancelButton;
    @FindBy(xpath="//button[@id='finish']")
    private WebElement finishButton;
    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    private WebElement totalPriceWithTax;
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public void pressCancelButton(){
        cancelButton.click();
    }
    public void clickFinishButton(){
        finishButton.click();
    }
    public String getTotalPriceWithTax(){
        waitForPresenceOfElement(driver,"//div[@class='summary_info_label summary_total_label']");
        return totalPriceWithTax.getText();
    }
}
