package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;


public class CheckoutAction {
    private final CheckoutPage page;

    public CheckoutAction(CheckoutPage page) {
        this.page = page;
    }
    public CheckoutOverviewPage fillOutDeliveryInformation(User user){
        if(user.getFirstName() != null){
            page.inputFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            page.inputLastName(user.getLastName());
        }
        if(user.getZipCode() != null){
            page.inputZipCode(user.getZipCode());
        }
        page.clickContinueButton();
        return new CheckoutOverviewPage(DriverSingleton.getInstance().getDriver());
    }
}
