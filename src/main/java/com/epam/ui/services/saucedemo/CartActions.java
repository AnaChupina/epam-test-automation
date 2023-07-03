package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CartPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import com.epam.ui.pages.saucedemo.InventoryPage;

public class CartActions extends LogoutActions {
    private final CartPage page;

    public CartActions(CartPage page) {
        super(page);
        this.page = page;
    }

    public CartPage getCartPage() {
        return page;
    }
    public int getNumberOfItemsInCart(){
        return page.getNumberOfItemsInCart();
    }
    public String[] getNamesOfItemsInCart (int numberOfItems){
        return page.getNamesOfItemsInCart(numberOfItems);
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
