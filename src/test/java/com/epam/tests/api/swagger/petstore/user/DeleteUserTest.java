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
    private static final Logger LOGGER = LogManager.getLogger(UserInteractionTest.class);
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");


    @BeforeEach
    public void setUp() {
        String user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        response = userHandler.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("Delete user by username = user1")
    public void deleteUserTest(){
        response = userHandler.deleteUser(username)
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
