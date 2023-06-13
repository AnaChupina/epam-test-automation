package com.epam.tests.webdriver.university;

import com.epam.pages.webdriver.university.WebDriverUniversityPage;
import com.epam.tests.saucedemo.CommonConditions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverUniversityTests extends CommonConditions {
    private String expectedClassOfDoubleClickButton = "div-double-click double";
    private String expectedMessageClickAndHoldButton = "Well done! keep holding that click now.....";
    private String expectedMessageAfterDragAndDrop = "Dropped!";
    private String expectedAlertMessage = "Well done you clicked on the link!";
    @Test
    @DisplayName("uni_test_1")
    public void checkDoubleClickButton (){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .doubleClick();
        assertEquals(expectedClassOfDoubleClickButton, page.getClassOfDoubleClickButton());
    }
    @Test
    @DisplayName("uni_test_2")
    public void checkClickAndHoldButton(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .clickAndHold();
        assertEquals(expectedMessageClickAndHoldButton, page.getMessageClickAndHoldButton());
    }
    @Test
    @DisplayName("uni_test_3")
    public void checkDragAndDropMethod(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .dragElementToTarget();
        assertEquals(expectedMessageAfterDragAndDrop, page.getMassageAfterDropElementToTarget());
    }
    @Test
    @DisplayName("uni_test_4")
    public void checkAlertMessage(){
        WebDriverUniversityPage page = new WebDriverUniversityPage(driver)
                .openPage()
                .clickHoverOverMeFirst();
        assertEquals(expectedAlertMessage,page.getAlertMessage());
    }
}
