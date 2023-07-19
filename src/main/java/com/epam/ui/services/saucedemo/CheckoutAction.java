package com.epam.ui.services.saucedemo;

import com.epam.ui.driver.DriverSingleton;
import com.epam.ui.model.User;
import com.epam.ui.pages.saucedemo.CheckoutOverviewPage;
import com.epam.ui.pages.saucedemo.CheckoutPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CheckoutAction {
    private static final Logger LOGGER = LogManager.getLogger(CheckoutAction.class);
    private final CheckoutPage page;
    public CheckoutAction(CheckoutPage page) {
        this.page = page;
    }
    public CheckoutOverviewPage fillOutDeliveryInformation(User user){
        if(user.getFirstName() != null){
            page.inputFirstName(user.getFirstName());
        } else {
            LOGGER.trace("First name is null");
        }
        if(user.getLastName() != null){
            page.inputLastName(user.getLastName());
        } else {
            LOGGER.trace("Last name is null");
        }
        if(user.getZipCode() != null){
            page.inputZipCode(user.getZipCode());
        } else {
            LOGGER.trace("Zipcode is null");
        }
        page.clickContinueButton();
        LOGGER.info("Delivery information was filled out");
        LOGGER.debug(user.toString());
        return new CheckoutOverviewPage(DriverSingleton.getInstance().getDriver());
    }
}
