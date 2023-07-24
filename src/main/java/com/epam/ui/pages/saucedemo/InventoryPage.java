package com.epam.ui.pages.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class InventoryPage extends BasePage{
    private static final Logger LOGGER = LogManager.getLogger(InventoryPage.class);
    private static final String URL = "https://www.saucedemo.com/inventory.html";
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement productSortContainer;
    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement productPrice;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public InventoryPage clickSortContainerButton(){
        waitForClickabilityOfElement(driver, productSortContainer);
        productSortContainer.click();
        LOGGER.info("Click sort container button");
        return this;
    }
    public InventoryPage choseSortingOption(String sortingRule){
        String element = String.format("//option[contains(text(),'%s')]", sortingRule);
        waitForClickabilityOfElement(driver, element);
        WebElement sortingOption = driver.findElement(By.xpath(element));
        sortingOption.click();
        LOGGER.info(String.format("Sorting option '%s' was chosen", sortingOption));
        return this;
    }
    public ArrayList<InventoryItem> getAllItemsOnPage(){
        String elementLocator = "//div[@class='inventory_item']";
        List<WebElement> elements = driver.findElements(By.xpath(elementLocator));
        ArrayList<InventoryItem> items = new ArrayList<>();
        for(WebElement element : elements){
            items.add(new InventoryItem(element));
        }
        return items;
    }
    public InventoryPage clickProduct(String productName){
        String element = String.format("//div[contains(text(), '%s')]", productName);
        waitForClickabilityOfElement(driver, element);
        WebElement product = driver.findElement(By.xpath(element));
        product.click();
        waitForPresenceOfElement(driver, "//div[@class='inventory_details_price']");
        LOGGER.info(String.format("Click product %s", productName));
        return this;
    }
    public String getCurrentProductPrice(){
        waitForPresenceOfElement(driver, "//div[@class='inventory_details_price']");
        LOGGER.trace("Current product price");
        LOGGER.trace(productPrice.getAttribute("innerText"));
        return productPrice.getAttribute("innerText");
    }
    public InventoryItem findItem(String productName){
        String elementLocator = String.format("//div[@class='inventory_item_name' and text() = '%s']//ancestor::div[@class='inventory_item']", productName);
        WebElement item = driver.findElement(By.xpath(elementLocator));
        LOGGER.trace(String.format("Found item '%s'", productName));
        return new InventoryItem(item);
    }
    public CartPage clickShoppingCartLink(){
        waitForClickabilityOfElement(driver, shoppingCartLink);
        shoppingCartLink.click();
        LOGGER.info("Click shopping cart link");
        return new CartPage(driver);
    }
}
