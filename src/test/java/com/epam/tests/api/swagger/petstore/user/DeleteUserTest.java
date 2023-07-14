package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.services.UserHandler;
import com.epam.api.utils.FileHandler;
import com.epam.api.utils.UserCreator;
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
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class);
    private UserHandler userHandle;
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");


    @BeforeEach
    public void setUp() {
        String user = UserCreator.createJsonUserObject();
        userHandle = new UserHandler();
        response = userHandle.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("api_test_user_8")
    public void deleteUserTest(){
        response = userHandle.deleteUser(username)
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
