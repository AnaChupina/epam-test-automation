package com.epam.api.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static com.epam.api.constant.Endpoint.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserHandler {
    public ValidatableResponse createUsersWithArray(String array){
        return given()
                .contentType(ContentType.JSON)
                .body(array)
                .when()
                .post(USER_CREATE_WITH_ARRAY_PATH)
                .then().log().all();
//                .body("message", equalTo("ok"))
//                .extract().response();
    }
    public Response createUser(String user){
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_PATH)
                .then().log().all()
                .extract().response();
    }
    public ValidatableResponse createUsersWithList(String list){
        return given()
                .contentType(ContentType.JSON)
                .body(list)
                .when()
                .post(USER_CREATE_WITH_LIST_PATH)
                .then().log().all();
//                .body("message", equalTo("ok"))
//                .extract().response();
    }
    public Response updateUserInformation(String updatedUser, String username){
        return given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put(USER_PATH + "/" + username)
                .then().log().all()
                .extract().response();
    }
    public ValidatableResponse getUserInformation(String username){
        return given()
                .when()
                .get(USER_PATH + "/" + username)
                .then().log().all();
    }
    public ValidatableResponse login(String username, String password){
        return given()
                .param("username", username)
                .param("password",password)
                .when()
                .get(USER_LOGIN_PATH)
                .then().log().all();
    }
    public ValidatableResponse logout(){
        return given()
                .when()
                .get(USER_LOGOUT_PATH)
                .then().log().all();
    }
    public ValidatableResponse deleteUser(String username){
        return given()
                .when()
                .delete(USER_PATH + "/" + username)
                .then().log().all();
    }

}
