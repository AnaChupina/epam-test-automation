package com.epam.ui.pages.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItem extends Item {
    private static final Logger LOGGER = LogManager.getLogger(InventoryItem.class);
    private final WebElement element;
    @FindBy(xpath = ".//button[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = ".//button[text()='Remove']")
    private WebElement removeFromCartButton;
    @FindBy(xpath = ".//div[@class='inventory_item_name']")
    private WebElement itemName;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartLink;

    public InventoryItem(WebElement element) {
        this.element = element;
        PageFactory.initElements(element,this);
    }
    public InventoryItem clickAddToCart(){
        addToCartButton.click();
        return this;
    }

    @Override
    public InventoryItem clickRemoveFromCart(){
        removeFromCartButton.click();
        LOGGER.info(String.format("Click remove from cart %s", this.getName()));
        return this;
    }
    public CartPage clickShoppingCartLink(){
        shoppingCartLink.click();
        LOGGER.info("Click shopping cart link");
        return new CartPage(DriverSingleton.getInstance().getDriver());
    }

    @Override
    public String getName(){
        return itemName.getText();
    }
}
