package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CartItem;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import com.epam.ui.pages.saucedemo.InventoryPage;

import java.util.ArrayList;

public class CartActions extends BasicActions {
    private final CartPage page;

    public CartActions(CartPage page) {
        super(page);
        this.page = page;
    }

    public CartPage getCartPage() {
        return page;
    }
    public ArrayList<CartItem> getAllCartItems (){
        return page.getAllItemsInCart();
    }
    public InventoryActions continueShopping(){
        page.clickContinueShoppingButton();
        InventoryPage inventoryPage = new InventoryPage(DriverSingleton.getInstance().getDriver());
        return new InventoryActions(inventoryPage);
    }
    public CheckoutActions goToCheckout(){
        page.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(DriverSingleton.getInstance().getDriver());
        return new CheckoutActions(checkoutPage);
    }

}
