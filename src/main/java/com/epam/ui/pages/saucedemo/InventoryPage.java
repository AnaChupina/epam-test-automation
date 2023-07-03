package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class InventoryPage extends BasePage{
    private static final String URL = "https://www.saucedemo.com/inventory.html";
    @FindBy(className = "product_sort_container")
    private WebElement productSortContainer;
    @FindBy(xpath = "//div[@id='inventory_container']//div[@class='inventory_item_name'][1]")
    private WebElement firstItemInContainer;
    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement productPrice;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void clickSortContainerButton(){
        waitForClickabilityOfElement(driver, productSortContainer);
        productSortContainer.click();
    }
    public void choseSortingOption(String sortingRule){
        String element = "//option[contains(text(),'" + sortingRule + "')]";
        waitForClickabilityOfElement(driver, element);
        WebElement sortingOption = driver.findElement(By.xpath(element));
        sortingOption.click();
    }
    public String getFirstItemOnPage(){
        return firstItemInContainer.getAttribute("innerText");
    }
    public void clickProduct(String productName){
        String element = "//div[contains(text(), '" + productName + "')]";
        waitForClickabilityOfElement(driver, element);
        WebElement product = driver.findElement(By.xpath(element));
        product.click();
    }
    public String getCurrentProductPrice(){
        waitForPresenceOfElement(driver, "//div[@class='inventory_details_price']");
        return productPrice.getAttribute("innerText");
    }
    //TODO: rename all products to items
    public InventoryItem findItem(String productName){
        String elementLocator = "//div[@class='inventory_item_name' and text() = '" + productName + "']//ancestor::div[@class='inventory_item']";
        WebElement item = driver.findElement(By.xpath(elementLocator));
        return new InventoryItem(item);
    }
    public void clickShoppingCartLink(){
        waitForClickabilityOfElement(driver, shoppingCartLink);
        shoppingCartLink.click();
    }
}
