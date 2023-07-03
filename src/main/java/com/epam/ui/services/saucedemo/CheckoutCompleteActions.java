package com.epam.ui.services.saucedemo;

import com.epam.ui.pages.saucedemo.CheckoutCompletePage;


public class CheckoutCompleteActions extends LogoutActions {
    private final CheckoutCompletePage page;

    public CheckoutCompleteActions(CheckoutCompletePage page) {
        super(page);
        this.page = page;
    }
    public String getCompleteMessage(){
        return page.getCompleteMessage();
    }
}
