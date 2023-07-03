package com.epam.ui.pages.saucedemo;

import com.epam.ui.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class CartPage extends BasePage{
    private static final String URL = "https://www.saucedemo.com/cart.html";
    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(id="checkout")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public CartPage openCart(){
        cartButton.click();
        return this;
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


    // TODO: Refactor this
    public int getNumberOfItemsInCart(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
        int i = 1;
        while (i<=6){
            String cartItemLocator = "//div[@class=\"cart_item\"]" + "[" + i + "]";
            try {
                WebElement cartItem = driver.findElement(By.xpath(cartItemLocator));
                i++;
            } catch (WebDriverException exp) {
                break;
            }
        }
        return i-1;//because on the last cycle an extra 1 is added
    }
    public String [] getNamesOfItemsInCart (int numberOfItems){
        String [] namesOfItems = new String [numberOfItems];
        int i = 1;
        for(int j=0; j<numberOfItems; j++){
            String cartItemLocator = "//div[@class='cart_item'][" + i + "]//div[@class='inventory_item_name']";
            try {
                WebElement cartItem = driver.findElement(By.xpath(cartItemLocator));
                namesOfItems[j] = cartItem.getText();
                i++;
            }catch (WebDriverException exp){
                break;
            }
        }
        return namesOfItems;
    }

}
