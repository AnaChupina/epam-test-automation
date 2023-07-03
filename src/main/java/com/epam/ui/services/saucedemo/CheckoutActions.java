package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import org.openqa.selenium.NotFoundException;

import static com.epam.ui.utils.waits.ExplicitWait.waitForClickabilityOfElement;

public class CheckoutActions extends LogoutActions {
    private final CheckoutPage page;

    public CheckoutActions(CheckoutPage page) {
        super(page);
        this.page = page;
    }
    public CheckoutOverviewActions fillOutDeliveryInformation(User user){
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
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(DriverSingleton.getInstance().getDriver());
        return new CheckoutOverviewActions(overviewPage);
    }
    public String getErrorMessage(){
        return page.getErrorMessage();
    }

}
