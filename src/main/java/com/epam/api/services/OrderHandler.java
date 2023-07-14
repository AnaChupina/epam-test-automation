package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static com.epam.api.constant.Endpoint.STORE_INVENTORY_PATH;
import static com.epam.api.constant.Endpoint.STORE_ORDER_PATH;
import static io.restassured.RestAssured.given;

public class OrderHandler {
    public ValidatableResponse placeOrderForPet(String order){
        return given()
                .contentType (ContentType.JSON)
                .body(order)
                .when()
                .post(STORE_ORDER_PATH)
                .then().log().all();
    }
    public ValidatableResponse getPurchaseOrderByID(int orderId) {
        return given().log().all()
                .when()
                .get(STORE_ORDER_PATH + "/"+ orderId)
                .then().log().all();
    }
    public ValidatableResponse deletePurchaseOrderByID(int orderId){
        return given()
                .when()
                .delete(STORE_ORDER_PATH + "/"+ orderId)
                .then().log().all();
    }
    public ValidatableResponse returnsPetInventoriesByStatus(){
        return given()
                .when()
                .get(STORE_INVENTORY_PATH)
                .then().log().all();
    }

}
