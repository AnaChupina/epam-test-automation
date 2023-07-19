package com.epam.ui.pages.saucedemo;

import com.epam.api.utils.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class BasePage {
    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);
    protected final WebDriver driver;
    @FindBy(xpath = "//button[text()='Open Menu']")
    private WebElement burgerMenuButton;
    @FindBy(xpath = "//div[@class='bm-menu']")
    private WebElement burgerMenuElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public BurgerMenu clickBurgerMenuButton(){
        burgerMenuButton.click();
        waitForPresenceOfElement(driver,"//div[@class='bm-menu']");
        LOGGER.info("Click burger menu button");
        return new BurgerMenu(burgerMenuElement);
    }
}
