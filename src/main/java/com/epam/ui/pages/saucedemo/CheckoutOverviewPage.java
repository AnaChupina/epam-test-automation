package com.epam.ui.pages.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class CheckoutOverviewPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(CheckoutOverviewPage.class);
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
    public InventoryPage clickCancelButton(){
        cancelButton.click();
        LOGGER.info("Click cancel button to cancel checkout");
        return new InventoryPage(driver);
    }
    public CheckoutCompletePage clickFinishButton(){
        finishButton.click();
        LOGGER.info("Click finish button to complete checkout");
        return new CheckoutCompletePage(driver);
    }
    public String getTotalPriceWithTax(){
        waitForPresenceOfElement(driver,"//div[@class='summary_info_label summary_total_label']");
        LOGGER.debug(totalPriceWithTax.getText());
        return totalPriceWithTax.getText();
    }
}
