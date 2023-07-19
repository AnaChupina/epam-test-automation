package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.UserDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.net.HttpURLConnection;

import static com.epam.testdata.api.swagger.petstore.UserTestData.ARRAY_TO_CREATE_USER;
import static com.epam.testdata.api.swagger.petstore.UserTestData.LIST_TO_CREATE_USER;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(CreateUserTest.class);
    private String user;
    private static Response response;

    @Test
    @DisplayName("Creates list of users with given input array")
    public void createUsersWithArrayTest(){
        response = userHandler.createUsersWithArray(ARRAY_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();
        LOGGER.info("Request to create users with Array was sent to the server");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Creates list of users with input array list")
    public void createUsersWithListTest(){
        response = userHandler.createUsersWithList(LIST_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();
        LOGGER.info("Request to create users with List was sent to the server");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Create user with username = user1")
    public void createUserTest(){
        user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        response = userHandler.createUser(user);
        LOGGER.info("Request to create user was sent to the server");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
