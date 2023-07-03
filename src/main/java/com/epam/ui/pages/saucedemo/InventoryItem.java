package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItem {
    private final WebElement element;
    @FindBy(xpath = "//button[text()='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removeFromCartButton;
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
}
