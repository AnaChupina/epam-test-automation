package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.InventoryItem;
import com.epam.ui.pages.saucedemo.InventoryPage;

import java.util.ArrayList;


public class InventoryActions extends BasicActions {
    private final InventoryPage page;

    public InventoryActions(InventoryPage page) {
        super(page);
        this.page = page;
    }
    public InventoryActions sortItems (String sortingRule) {
        page.clickSortContainerButton();
        page.choseSortingOption(sortingRule);
        return this;
    }
    public ArrayList<InventoryItem> getAllInventoryItems(){
        return page.getAllItemsOnPage();
    }
    public String getProductPrice(String productName){
        page.clickProduct(productName);
        return page.getCurrentProductPrice();
    }
    public InventoryActions addItemToCart(String productName){
        page.findItem(productName).clickAddToCart();
        return this;
    }
    public CartActions goToShoppingCart(){
        page.clickShoppingCartLink();
        CartPage cartPage = new CartPage(DriverSingleton.getInstance().getDriver());
        return new CartActions(cartPage);
    }
    public InventoryActions removeItemFromCart(String productName){
        page.findItem(productName).clickRemoveFromCart();
        return this;
    }
}
