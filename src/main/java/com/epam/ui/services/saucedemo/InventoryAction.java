package com.epam.ui.services.saucedemo;

import com.epam.ui.pages.saucedemo.InventoryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class InventoryAction {
    private static final Logger LOGGER = LogManager.getLogger(InventoryAction.class);
    private final InventoryPage page;

    public InventoryAction(InventoryPage page) {
        this.page = page;
    }
    public InventoryAction addItemToCart(String productName){
        page.findItem(productName).clickAddToCart();
        LOGGER.info(String.format("Item '%s' was added to cart", productName));
        return this;
    }
}
