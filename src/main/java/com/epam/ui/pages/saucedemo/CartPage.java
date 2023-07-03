package com.epam.ui.pages.saucedemo;

import com.epam.ui.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class CartPage extends BasePage{
    private static final String URL = "https://www.saucedemo.com/cart.html";
    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;
    @FindBy(xpath="//button[@id='checkout']")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isItCartPage(){
        try {
            waitForPresenceOfElement(driver,"//span[contains(text(), 'Cart')]");
        }catch (WebDriverException exp){
            return false;
        }
        return true;
    }
    public CartPage clickContinueShoppingButton(){
        continueShoppingButton.click();
        return this;
    }
    public CartPage clickCheckoutButton(){
        checkoutButton.click();
        return this;
    }
    public ArrayList<CartItem> getAllItemsInCart(){
        String elementLocator = "//div[@class='cart_item']";
        List<WebElement> elements = driver.findElements(By.xpath(elementLocator));
        ArrayList<CartItem> items = new ArrayList<>();
        for(WebElement element : elements){
            CartItem item = new CartItem(element);
            items.add(item);
        }
        return items;
    }

}
