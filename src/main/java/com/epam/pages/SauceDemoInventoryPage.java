package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.waits.ExplicitWait.waitForPresenceOfElement;

public class SauceDemoInventoryPage {
    private static final String URL = "https://www.saucedemo.com/inventory.html";
    private final WebDriver driver;
    @FindBy(className = "product_sort_container")
    private WebElement productSortContainer;
    @FindBy(xpath = "//div[@id='inventory_container']//div[@class='inventory_item_name'][1]")
    private WebElement firstItemInContainer;
    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement productPrice;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;
    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Backpack')]")
    private WebElement cart_item;

    public SauceDemoInventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String sortItems (String sortingRule) {
        String element = "//option[contains(text(),'" + sortingRule + "')]";
        waitForClickabilityOfElement(driver, productSortContainer);
        productSortContainer.click();
        waitForClickabilityOfElement(driver, element);
        WebElement sortingOption = driver.findElement(By.xpath(element));
        sortingOption.click();
        return firstItemInContainer.getAttribute("innerText");
    }
    public String getProductPrice(String productName){
        String element = "//div[contains(text(), '" + productName + "')]";
        waitForClickabilityOfElement(driver, element);
        WebElement product = driver.findElement(By.xpath(element));
        product.click();
        waitForPresenceOfElement(driver, "//div[@class='inventory_details_price']");
        return productPrice.getAttribute("innerText");
    }
    public SauceDemoInventoryPage addToCart(String productName) {
        //The product name should be written in the following format: "sauce-labs-backpack".
        String element = "//button[@id='add-to-cart-" + productName + "']";
        WebElement addToCartButton = driver.findElement(By.xpath(element));
        addToCartButton.click();
        return this;
    }
    public boolean isProductInCart(String productName){
        shoppingCartLink.click();
        String element = "//div[contains(text(),'" + productName + "')]";
        //div[contains(text(),'Sauce Labs Backpack')]
        try{
            WebElement productInCart = driver.findElement(By.xpath(element));
        }catch (WebDriverException exp){
            return false;
        }
        return true;
    }
    public SauceDemoInventoryPage removeProductFromCart (String productName){
        String element = "//button[@id='remove-" + productName + "']";
        WebElement removeFromCartButton = driver.findElement(By.xpath(element));
        removeFromCartButton.click();
        //sauce-labs-bike-light
        return this;
    }
}
