package com.epam.ui.services.saucedemo;

import com.epam.ui.pages.saucedemo.InventoryPage;


public class InventoryAction {
    private final InventoryPage page;

    public InventoryAction(InventoryPage page) {
        this.page = page;
    }
    public InventoryAction addItemToCart(String productName){
        page.findItem(productName).clickAddToCart();
        return this;
    }
}
