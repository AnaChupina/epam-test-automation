package com.epam.pages;

import com.epam.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.waits.ExplicitWait.waitForClickabilityOfElement;
import static com.epam.waits.ExplicitWait.waitForPresenceOfElement;

public class SauceDemoCartPage {
    private static final String URL = "https://www.saucedemo.com/cart.html";
    private final WebDriver driver;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(id="checkout")
    private WebElement checkoutButton;
    @FindBy(id="first-name")
    private WebElement firstNameField;
    @FindBy(id="last-name")
    private WebElement lastNameField;
    @FindBy(id="postal-code")
    private WebElement zipCode;
    @FindBy(id="continue")
    private WebElement continueButton;
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement errorMessageFromCheckoutProcess;
    @FindBy(id="cancel")
    private WebElement cancelButtonOnCheckoutOverviewPage;
    @FindBy(id="finish")
    private WebElement finishButton;
    @FindBy(className = "complete-header")
    private WebElement completeMessage;
    @FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
    private WebElement totalPriceWithTax;
    @FindBy(xpath = "//button[contains(@id,'menu')]")
    private WebElement burgerMenuButton;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public SauceDemoCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public SauceDemoCartPage openCart(){
        cartButton.click();
        return this;
    }
    public boolean isItCartPage(){
        try {
            waitForPresenceOfElement(driver,"//span[contains(text(), 'Cart')]");
        }catch (WebDriverException exp){
            return false;
        }
        return true;
    }
    public SauceDemoCartPage pushContinueShoppingButton(){
        continueShoppingButton.click();
        return this;
    }
    public SauceDemoCartPage pushCheckoutButton(){
        checkoutButton.click();
        return this;
    }
    public SauceDemoCartPage checkoutProcess(User user){
        waitForClickabilityOfElement(driver,firstNameField);
        if(user.getFirstName() != null){
            firstNameField.sendKeys(user.getFirstName());
        }
        if(user.getLastName() != null){
            lastNameField.sendKeys(user.getLastName());
        }
        if(user.getZipCode() != null){
            zipCode.sendKeys(user.getZipCode());
        }
        continueButton.click();
        return this;
    }
    public String getErrorMessageFromCheckoutProcess(){
        return errorMessageFromCheckoutProcess.getText();
    }
    public SauceDemoCartPage pressCancelButtonOnCheckoutOverviewPage(){
        cancelButtonOnCheckoutOverviewPage.click();
        return this;
    }
    public SauceDemoCartPage completeOrder(){
        finishButton.click();
        return this;
    }
    public String getCompleteMessage(){
        return completeMessage.getText();
    }

    public String getTotalPriceWithTax(){
        waitForPresenceOfElement(driver,"//div[@class='summary_info_label summary_total_label']");
        return totalPriceWithTax.getText();
    }
    public SauceDemoCartPage logout(){
        burgerMenuButton.click();
        logoutButton.click();
        return this;
    }
    public int getNumberOfItemsInCart(){
        waitForPresenceOfElement(driver, "//div[@class=\"cart_item\"]");
        int i = 1;
        while (i<=6){
            String cartItemLocator = "//div[@class=\"cart_item\"]" + "[" + i + "]";
            try {
                WebElement cartItem = driver.findElement(By.xpath(cartItemLocator));
                i++;
            } catch (WebDriverException exp) {
                break;
            }
        }
        return i-1;//because on the last cycle an extra 1 is added
    }
    public String [] getNamesOfItemsInCart (int numberOfItems){
        String [] namesOfItems = new String [numberOfItems];
        int i = 1;
        for(int j=0; j<numberOfItems; j++){
            String cartItemLocator = "//div[@class='cart_item'][" + i + "]//div[@class='inventory_item_name']";
            try {
                WebElement cartItem = driver.findElement(By.xpath(cartItemLocator));
                namesOfItems[j] = cartItem.getText();
                i++;
            }catch (WebDriverException exp){
                break;
            }
        }
        return namesOfItems;
    }

}
