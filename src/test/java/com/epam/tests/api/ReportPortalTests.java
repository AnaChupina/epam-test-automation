package com.epam.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReportPortalTests {
    private static final Logger LOGGER = LogManager.getLogger(ReportPortalTests.class);
    @BeforeAll
    public static void setBaseParam()throws InterruptedException{
        RestAssured.baseURI = "https://reportportal.epam.com";
        RestAssured.basePath = "/api";
        LOGGER.info("Inside ReportPortalTests beforeAll ");
    }
    @BeforeEach
    public void beforeEach() {
        LOGGER.info("Inside ReportPortalTests beforeEach ");
    }

    /** Не знаю как проверить, что все ланчи на месте, кроме как сверить количество totalElements, но оно постоянно меняется
     */
    @Test
    @DisplayName("rp_test_1")
    public void getAllLaunchesTest(){
        LOGGER.info("Inside ReportPortalTests test ");
        String bearerToken = "4742d968-4de6-46e4-98f9-bc2b9632872e";
        Response res =
                given()
                        .headers(
                "Authorization",
                "Bearer " + bearerToken)
                .when()
                        .get("/v1/anastasia_chupina_personal/launch")
                .then().log().all()
                        .contentType(ContentType.JSON)
                        .body("content[0].owner", equalTo("anastasia_chupina"))
                        .extract().response();
        String status = res
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
