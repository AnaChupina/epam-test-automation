package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.InventoryItem;
import com.epam.ui.pages.saucedemo.InventoryPage;

import java.util.ArrayList;


public class InventoryActions {
    private final InventoryPage page;

    public InventoryActions(InventoryPage page) {
        this.page = page;
    }
    public InventoryActions addItemToCart(String productName){
        page.findItem(productName).clickAddToCart();
        return this;
    }
}
