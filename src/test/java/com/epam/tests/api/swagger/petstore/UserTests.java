package com.epam.tests.api.swagger.petstore;

import com.epam.utils.api.UserCreator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTests {
    private static final Logger LOGGER = LogManager.getLogger(UserTests.class);

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        LOGGER.info("Inside SwaggerUserTests beforeEach ");
        RequestSpecification requestSpec = given()
                .contentType(ContentType.JSON)
                .body(UserCreator.createJsonUserObject());
        requestSpec.when()
                .post("/user");
        LOGGER.info("User with username = user1 was created ");
    }
    @Test
    @DisplayName("api_test_user_1")
    public void createUsersWithArrayTest(){
        given()
                .contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("/user/createWithArray")
                .then().log().all()
                .body("message", equalTo("ok"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_2")
    public void createUsersWithListTest(){
        given()
                .contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("/user/createWithList")
                .then().log().all()
                .body("message", equalTo("ok"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_3")
    public void updateUserInformationTest_UserId_1(){
        given()
                .contentType(ContentType.JSON)
                .body(UserCreator.createJsonUserObject())
                .when()
                .put("/user/user1")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_4")
    public void getUserInformationTest_UserId_1(){
        given()
                .when()
                .get("/user/user1")
                .then().log().all()
                .body("firstName", equalTo("John"))
                .body("username", equalTo("user1"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_5")
    public void loginTest_UserId_1(){
        given()
                .param("username", "user1")
                .param("password","1234")
                .when()
                .get("/user/login?username=user1&password=1234")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_6")
    public void logoutTest_UserId_1(){
        given()
                .when()
                .get("/user/logout")
                .then().log().all()
                .body("message",equalTo("ok"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_7")
    public void createUserTest_UserId_1(){
        given()
                .contentType(ContentType.JSON)
                .body(UserCreator.createJsonUserObject())
                .when()
                .post("/user")
                .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_8")
    public void deleteUserTest_UserId_1(){
        given()
                .when()
                .delete("/user/user1")
                .then().log().all()
                .body("message",equalTo("user1"))
                .assertThat().statusCode(200);
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerUserTests afterEach ");
        RestAssured.reset();
    }
}
