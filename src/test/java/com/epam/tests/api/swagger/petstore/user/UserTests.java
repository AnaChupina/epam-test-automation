package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.services.UserHandle;
import com.epam.api.utils.TestDataReader;
import com.epam.api.utils.UserCreator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static com.epam.api.config.Configuration.*;
import static com.epam.testdata.api.swagger.petstore.UserTestData.ARRAY_TO_CREATE_USER;
import static com.epam.testdata.api.swagger.petstore.UserTestData.LIST_TO_CREATE_USER;


public class UserTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class);
    private String user;
    private UserHandle userHandle;
    private static Response response;
    private final String username = TestDataReader.getTestData("username");
    private final String firstName = TestDataReader.getTestData("first.name");
    private final String password = TestDataReader.getTestData("password");


    @BeforeEach
    public void setUp() {
        user = UserCreator.createJsonUserObject();
        userHandle = new UserHandle();
        response = userHandle.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("api_test_user_1")
    public void createUsersWithArrayTest(){
        response = userHandle.createUsersWithArray(ARRAY_TO_CREATE_USER);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_2")
    public void createUsersWithListTest(){
        response = userHandle.createUsersWithList(LIST_TO_CREATE_USER);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_3")
    public void updateUserInformationTest(){
        response = userHandle.updateUserInformation(user, username);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_4")
    public void getUserInformationTest(){
        response = userHandle.getUserInformation(username,firstName);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_5")
    public void loginTest(){
        response = userHandle.login(username,password);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_6")
    public void logoutTest(){
        response = userHandle.logout();
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_user_7")
    public void createUserTest(){
        response = userHandle.createUser(user);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerUserTests afterEach ");
        response = userHandle.deleteUser(username);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());

    }
}
