package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.InventoryPage;


public class InventoryActions extends LogoutActions {
    private final InventoryPage page;

    public InventoryActions(InventoryPage page) {
        super(page);
        this.page = page;
    }
    //TODO: split into sort method and get first
    public String sortItems (String sortingRule) {
        page.clickSortContainerButton();
        page.choseSortingOption(sortingRule);
        return page.getFirstItemOnPage();
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
