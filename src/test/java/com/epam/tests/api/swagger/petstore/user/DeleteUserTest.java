package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.utils.FileHandler;
import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.UserDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;


public class DeleteUserTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserTest.class);
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");


    @BeforeEach
    public void setUp() {
        String user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        LOGGER.info("JSON request with user was created");
        LOGGER.info(user);
        response = userHandler.createUser(user);
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        LOGGER.info("Request to create user was sent to the server");
        LOGGER.debug(response.asString());
    }
    @Test
    @DisplayName("Delete user by username = user1")
    public void deleteUserTest(){
        response = userHandler.deleteUser(username)
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
        LOGGER.info("Request to delete the user was sent and server response was received");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        Response newResponse = userHandler.getUserInformation(username)
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_NOT_FOUND,newResponse.statusCode());
    }
}
