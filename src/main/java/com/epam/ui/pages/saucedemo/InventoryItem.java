package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItem {
    private final WebElement element;
    @FindBy(xpath = ".//button[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = ".//button[text()='Remove']")
    private WebElement removeFromCartButton;
    @FindBy(xpath = ".//div[@class='inventory_item_name']")
    private WebElement itemName;

    public InventoryItem(WebElement element) {
        this.element = element;
        PageFactory.initElements(element,this);
    }
    public void clickAddToCart(){
        addToCartButton.click();
    }
    public void clickRemoveFromCart(){
        removeFromCartButton.click();
    }
    public String getName(){
        return itemName.getText();
    }
}
