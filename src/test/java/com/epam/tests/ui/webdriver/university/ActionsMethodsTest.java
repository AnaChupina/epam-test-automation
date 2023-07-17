package com.epam.tests.ui.webdriver.university;

import com.epam.ui.pages.webdriver.university.WebDriverUniversityPage;
import com.epam.tests.base.BaseUITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class was developed to try different methods from Actions
 */
public class ActionsMethodsTest extends BaseUITest {
    private String expectedClassOfDoubleClickButton = "div-double-click double";
    private String expectedMessageClickAndHoldButton = "Well done! keep holding that click now.....";
    private String expectedMessageAfterDragAndDrop = "Dropped!";
    private String expectedAlertMessage = "Well done you clicked on the link!";
    @Test
    @DisplayName("Try methods doubleClick")
    public void checkDoubleClickButton (){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .doubleClick();
        assertEquals(expectedClassOfDoubleClickButton, page.getClassOfDoubleClickButton());
    }
    @Test
    @DisplayName("Try method clickAndHold")
    public void checkClickAndHoldButton(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .clickAndHold();
        assertEquals(expectedMessageClickAndHoldButton, page.getMessageClickAndHoldButton());
    }
    @Test
    @DisplayName("Try method dragAndDrop")
    public void checkDragAndDropMethod(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .dragElementToTarget();
        assertEquals(expectedMessageAfterDragAndDrop, page.getMassageAfterDropElementToTarget());
    }
    @Test
    @DisplayName("Try method moveToElement")
    public void checkAlertMessage(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .clickHoverOverMeFirst();
        assertEquals(expectedAlertMessage,page.getAlertMessage());
    }
}
