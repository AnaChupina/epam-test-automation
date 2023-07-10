package com.epam.api.services;

import com.epam.api.utils.TestDataReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.epam.api.constant.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserHandle {
    public Response createUsersWithArray(String array){
        return given()
                .contentType(ContentType.JSON)
                .body(array)
                .when()
                .post(USER_CREATE_WITH_ARRAY)
                .then().log().all()
                .body("message", equalTo("ok"))
                .extract().response();
    }
    public Response createUser(String user){
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER)
                .then().log().all()
                .extract().response();
    }
    public Response createUsersWithList(String list){
        return given()
                .contentType(ContentType.JSON)
                .body(list)
                .when()
                .post(USER_CREATE_WITH_LIST)
                .then().log().all()
                .body("message", equalTo("ok"))
                .extract().response();
    }
    public Response updateUserInformation(String updatedUser, String username){
        return given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put(USER + "/" + username)
                .then().log().all()
                .extract().response();
    }
    public Response getUserInformation(String username, String checkNameInResponse){
        return given()
                .when()
                .get(USER + "/" + username)
                .then().log().all()
                .extract().response();
    }
    public Response login(String username, String password){
        return given()
                .param("username", username)
                .param("password",password)
                .when()
                .get(USER_LOGIN)
                .then().log().all()
                .extract().response();
    }
    public Response logout(){
        return given()
                .when()
                .get(USER_LOGOUT)
                .then().log().all()
                .body("message",equalTo("ok"))
                .extract().response();
    }
    public Response deleteUser(String username){
        return given()
                .when()
                .delete(USER + "/" + username)
                .then().log().all()
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
    }

}
