package com.epam.tests.api.swagger.petstore.user;

import com.epam.api.services.UserHandle;
import com.epam.api.utils.TestDataReader;
import com.epam.api.utils.UserCreator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epam.api.config.Configuration.STATUS_CODE;

public class DeleteUserTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class);
    private UserHandle userHandle;
    private static Response response;
    private final String username = TestDataReader.getTestData("username");


    @BeforeEach
    public void setUp() {
        String user = UserCreator.createJsonUserObject();
        userHandle = new UserHandle();
        response = userHandle.createUser(user);
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("api_test_user_8")
    public void deleteUserTest(){
        response = userHandle.deleteUser(username);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
}
