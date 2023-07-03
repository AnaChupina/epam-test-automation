package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.pages.saucedemo.CheckoutCompletePage;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.InventoryPage;

public class CheckoutOverviewActions extends BasicActions {
    private final CheckoutOverviewPage page;

    public CheckoutOverviewActions(CheckoutOverviewPage page) {
        super(page);
        this.page = page;
    }

    public InventoryActions cancelCheckout(){
        page.pressCancelButton();
        InventoryPage inventoryPage = new InventoryPage(DriverSingleton.getInstance().getDriver());
        return new InventoryActions(inventoryPage);
    }
    public CheckoutCompleteActions finishCheckout(){
        page.clickFinishButton();
        CheckoutCompletePage completePage = new CheckoutCompletePage(DriverSingleton.getInstance().getDriver());
        return new CheckoutCompleteActions(completePage);
    }
    public String getTotalPriceWithTax(){
        return page.getTotalPriceWithTax();
    }
}
