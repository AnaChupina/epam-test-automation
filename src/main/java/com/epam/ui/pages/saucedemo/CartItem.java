package com.epam.ui.pages.saucedemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartItem extends Item {
    private static final Logger LOGGER = LogManager.getLogger(CartItem.class);
    private final WebElement element;
    @FindBy(xpath = ".//button[text()='Remove']")
    private WebElement removeFromCartButton;
    @FindBy(xpath = ".//div[@class='inventory_item_name']")
    private WebElement itemName;

    public CartItem(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }

    @Override
    public CartItem clickRemoveFromCart(){
        removeFromCartButton.click();
        LOGGER.info("Cart item was removed");
        return this;
    }

    @Override
    public String getName(){
        return itemName.getText();
    }

}
