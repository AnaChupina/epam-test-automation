package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.services.UserHandler;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class UserTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class);
    private String user;
    private UserHandler userHandle;
    private static Response response;
    private final String username = FileHandler.getDataFromProperties("petstoretestdata.properties","username");
    private final String firstName = FileHandler.getDataFromProperties("petstoretestdata.properties","first.name");
    private final String password = FileHandler.getDataFromProperties("petstoretestdata.properties","password");


    @BeforeEach
    public void setUp() {
        user = ObjectToJsonConvertor.convertObjectToJson(UserDataGenerator.createUser());
        userHandle = new UserHandler();
        response = userHandle.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("api_test_user_1")
    public void createUsersWithArrayTest(){
        response = userHandle.createUsersWithArray(ARRAY_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_2")
    public void createUsersWithListTest(){
        response = userHandle.createUsersWithList(LIST_TO_CREATE_USER)
                .body("message", equalTo("ok"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_3")
    public void updateUserInformationTest(){
        response = userHandle.updateUserInformation(user, username);
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_4")
    public void getUserInformationTest(){
        response = userHandle.getUserInformation(username)
                .body("username", equalTo(username))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_5")
    public void loginTest(){
        response = userHandle.login(username,password)
                .body("message", containsString("logged in user session:"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_6")
    public void logoutTest(){
        response = userHandle.logout()
                .body("message",equalTo("ok"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_7")
    public void createUserTest(){
        response = userHandle.createUser(user);
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerUserTests afterEach ");
        response = userHandle.deleteUser(username)
                .contentType(ContentType.JSON)
                .body("message",equalTo(username))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());

    }
}
