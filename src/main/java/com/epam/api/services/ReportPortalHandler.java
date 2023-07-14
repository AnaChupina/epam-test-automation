package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static com.epam.api.constant.Endpoint.REPORT_PORTAL_ALL_LAUNCHES_PATH;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReportPortalHandler {
    public ValidatableResponse getAllLaunches(String bearerToken){
        return given()
                .headers(
                        "Authorization",
                        "Bearer " + bearerToken)
                .when()
                .get(REPORT_PORTAL_ALL_LAUNCHES_PATH)
                .then().log().all();
    }
}
