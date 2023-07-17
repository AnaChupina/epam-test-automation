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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class UserInteractionTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(UserInteractionTest.class);
    private String user;
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");
    private final String firstName = FileHandler.getDataFromProperties("petstoretestdata.properties","first.name");
    private final String password = FileHandler.getDataFromProperties("petstoretestdata.properties","password");


    @BeforeEach
    public void setUp() {
        user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        response = userHandler.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }

    @Test
    @DisplayName("Updated user")
    public void updateUserInformationTest(){
        response = userHandler.updateUserInformation(user, username);
        //TODO: проверь что изменилось?
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Get user information by username")
    public void getUserInformationTest(){
        response = userHandler.getUserInformation(username)
                .body("username", equalTo(username))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("logs user into the system")
    public void loginTest(){
        response = userHandler.login(username,password)
                .body("message", containsString("logged in user session:"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Logs out current logged in user session")
    public void logoutTest(){
        response = userHandler.login(username,password)
                .body("message", containsString("logged in user session:"))
                .extract().response();
        response = userHandler.logout()
                .body("message",equalTo("ok"))
                .extract().response();
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
