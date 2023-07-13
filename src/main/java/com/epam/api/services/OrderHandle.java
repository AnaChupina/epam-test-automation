package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.epam.api.constant.Endpoints.STORE_INVENTORY;
import static com.epam.api.constant.Endpoints.STORE_ORDER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderHandle {
    public Response placeOrderForPet(String order){
        return given()
                .contentType (ContentType.JSON)
                .body(order)
                .when()
                .post(STORE_ORDER)
                .then().log().all()
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
    }
    public Response getPurchaseOrderByID(int orderId) {
        return given().log().all()
                .when()
                .get(STORE_ORDER + "/"+ orderId)
                .then().log().all()
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
    }
    public Response deletePurchaseOrderByID(int orderId){
        return given()
                .when()
                .delete(STORE_ORDER + "/"+ orderId)
                .then().log().all()
                .body("message", equalTo("1"))
                .extract().response();
    }
    public Response returnsPetInventoriesByStatus(){
        return given()
                .when()
                .get(STORE_INVENTORY)
                .then().log().all()
                .extract().response();
    }

}
