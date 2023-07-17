package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.utils.FileHandler;
import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.UserDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.net.HttpURLConnection;

import static com.epam.testdata.api.swagger.petstore.UserTestData.ARRAY_TO_CREATE_USER;
import static com.epam.testdata.api.swagger.petstore.UserTestData.LIST_TO_CREATE_USER;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(UserInteractionTest.class);
    private String user;
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");

    @BeforeEach
    public void setUp() {
        user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        response = userHandler.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }

    @Test
    @DisplayName("Creates list of users with given input array")
    public void createUsersWithArrayTest(){
        response = userHandler.createUsersWithArray(ARRAY_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Creates list of users with input array list")
    public void createUsersWithListTest(){
        response = userHandler.createUsersWithList(LIST_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Create user with username = user1")
    public void createUserTest(){
        response = userHandler.createUser(user);
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerUserTests afterEach ");
        response = userHandler.deleteUser(username)
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
