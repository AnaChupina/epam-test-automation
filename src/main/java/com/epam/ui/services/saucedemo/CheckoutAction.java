package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;

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
        // TODO: Ask if throwing an exception here is acceptable
//        try {
//            String error = page.getErrorMessage();
//            throw new RuntimeException(error);
//        } catch (NotFoundException ex) {
//            CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(DriverSingleton.getInstance().getDriver());
//            return new CheckoutOverviewActions(overviewPage);
//        }
        return new CheckoutOverviewPage(DriverSingleton.getInstance().getDriver());
    }
}
