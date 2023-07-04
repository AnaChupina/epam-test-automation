package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.epam.api.constant.Endpoints.REPORT_PORTAL_ALL_LAUNCHES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReportPortalHandle {
    public Response getAllLaunches(String bearerToken){
        return given()
                .headers(
                        "Authorization",
                        "Bearer " + bearerToken)
                .when()
                .get(REPORT_PORTAL_ALL_LAUNCHES)
                .then().log().all()
                .contentType(ContentType.JSON)
                .body("content[0].owner", equalTo("anastasia_chupina"))
                .extract().response();
    }
}
