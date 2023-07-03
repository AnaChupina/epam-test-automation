package com.epam.ui.pages.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartItem {
    private final WebElement element;
    @FindBy(xpath = ".//button[text()='Remove']")
    private WebElement removeFromCartButton;
    @FindBy(xpath = ".//div[@class='inventory_item_name']")
    private WebElement itemName;

    public CartItem(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public void clickRemoveFromCart(){
        removeFromCartButton.click();
    }
    public String getName(){
        return itemName.getText();
    }
}
