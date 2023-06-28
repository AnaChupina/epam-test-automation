package com.epam.ui.pages.webdriver.university;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.ui.utils.waits.ExplicitWait.waitForPresenceOfElement;

public class WebDriverUniversityPage {
    private static final String URL = "http://webdriveruniversity.com/Actions/index.html";
    private final WebDriver driver;
    @FindBy (xpath = "//div[@id='double-click']")
    private WebElement doubleClickButton;
    @FindBy (xpath = "//div[@id='click-box']")
    private WebElement clickAndHoldButton;
    @FindBy(xpath = "//div[@id='draggable']")
    private WebElement dragMeToTargetElement;
    @FindBy(xpath = "//div[@id='droppable']")
    private WebElement dropHereElement;
    @FindBy(xpath = "//div[@id='droppable']//b")
    private WebElement messageOfDroppableElement;
    @FindBy(xpath = "//button[contains(text(),'First!')]")
    private WebElement hoverOverMeFirst;
    @FindBy(xpath = "//button[contains(text(),'First!')]//..//a")
    private WebElement firstLink;

    public WebDriverUniversityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebDriverUniversityPage openPage() {
        driver.get(URL);
        waitForPresenceOfElement(driver, "//h1[@id='main-header']");
        return this;
    }
    public WebDriverUniversityPage doubleClick (){
        new Actions(driver).doubleClick(doubleClickButton).build().perform();
        return this;
    }
    public String getClassOfDoubleClickButton(){
        return doubleClickButton.getAttribute("className");
    }
    public WebDriverUniversityPage clickAndHold(){
        new Actions(driver).clickAndHold(clickAndHoldButton).build().perform();
        return this;
    }
    public String getMessageClickAndHoldButton(){
        return clickAndHoldButton.getText();
    }
    public WebDriverUniversityPage dragElementToTarget(){
        new Actions(driver).dragAndDrop(dragMeToTargetElement,dropHereElement).build().perform();
        return this;
    }
    public String getMassageAfterDropElementToTarget(){
        return messageOfDroppableElement.getAttribute("innerText");
    }
    public WebDriverUniversityPage clickHoverOverMeFirst(){
        new Actions(driver).moveToElement(hoverOverMeFirst).build().perform();
        firstLink.click();
        return this;
    }
    public String getAlertMessage(){
        return driver.switchTo().alert().getText();
    }
}
