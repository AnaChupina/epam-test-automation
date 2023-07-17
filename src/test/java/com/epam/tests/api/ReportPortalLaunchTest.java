package com.epam.tests.api;

import com.epam.api.services.ReportPortalHandler;
import com.epam.api.utils.FileHandler;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static com.epam.api.config.Configuration.BASE_PATH_REPORT_PORTAL;
import static com.epam.api.config.Configuration.BASE_URL_REPORT_PORTAL;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReportPortalLaunchTest {
    private static final Logger LOGGER = LogManager.getLogger(ReportPortalLaunchTest.class);
    private static Response response;
    private static ReportPortalHandler reportPortalHandle;
    private final String bearerToken = FileHandler.getDataFromProperties("reportportalconnection.properties", "bearer.token.report.portal");
    @BeforeAll
    public static void setBaseParam()throws InterruptedException{
        RestAssured.baseURI = BASE_URL_REPORT_PORTAL;
        RestAssured.basePath = BASE_PATH_REPORT_PORTAL;
        reportPortalHandle = new ReportPortalHandler();
        LOGGER.info("Inside ReportPortalTests beforeAll ");
    }
    @BeforeEach
    public void beforeEach() {
        LOGGER.info("Inside ReportPortalTests beforeEach ");
    }
    @Test
    @DisplayName("rp_test_1")
    public void getAllLaunchesTest(){
        LOGGER.info("Inside ReportPortalTests test ");
        response = reportPortalHandle.getAllLaunches(bearerToken)
                .contentType(ContentType.JSON)
                .body("content[0].owner", equalTo("anastasia_chupina"))
                .extract().response();
        String status = response
                .path("content[0].status");
        assertEquals(status, "PASSED");
    }


    @AfterAll
    public void afterAll() throws InterruptedException {
        LOGGER.info("Inside ReportPortalTests afterAll");
    }
    @AfterEach
    public void afterEach() {
        LOGGER.info("Inside ReportPortalTests afterEach ");
    }
}
