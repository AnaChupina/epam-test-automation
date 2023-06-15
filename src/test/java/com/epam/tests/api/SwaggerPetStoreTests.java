package com.epam.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SwaggerPetStoreTests {
    private static final Logger LOGGER = LogManager.getLogger(SwaggerPetStoreTests.class);

    @BeforeAll
    public static void setBaseParam()throws InterruptedException{
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        LOGGER.info("Inside SwaggerPetStoreTests beforeAll ");
    }
    @BeforeEach
    public void beforeEach() {
        LOGGER.info("Inside SwaggerPetStoreTests beforeEach ");
    }

    @Test
    @DisplayName("api_test_pet_1")
    public void addNewPetToStoreTest_petId_1(){
        LOGGER.info("Inside addNewPetToStoreTest test ");
        given()
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
        .when()
                .contentType (ContentType.JSON)
                .post("/pet")
        .then().log().all()
                .body("name", equalTo("doggie"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_2")
    public void updateAnExistingPetTest_petId_1(){
        given()
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"Doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
        .when()
                .contentType (ContentType.JSON)
                .put("/pet")
        .then().log().all()
                .body("name", equalTo("Doggie"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_3")
    public void findPetsByStatus_Available(){
        given()
                .param("status","available")
        .when()
                .get("/pet/findByStatus?status=available")
        .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_4")
    public void findPetByIDTest_petId_9223372036854624013(){
        given()
        .when()
                .get("/pet/9223372036854624013")
        .then().log().all()
                .body("name", equalTo("Leo"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_5")
    public void deletePetTest_petId_1(){
        given()
        .when()
                .delete("/pet/1")
        .then().log().all()
                .body("message", equalTo("1"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_pet_6")
    public void uploadsPetImageTest_petId_1(){
        String location = "src/test/resources/sobaka.jpeg";
        File petImage = new File(location);
        given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata","data")
                .multiPart("file", petImage)
        .when()
                .post("/pet/1/uploadImage")
        .then().log().all()
                .body("message", equalTo("additionalMetadata: data\nFile uploaded to ./sobaka.jpeg, 148538 bytes"))
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName("api_test_store_1")
    public void placeOrderForPetTest(){
        given()
                .contentType (ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"petId\": 1,\n" +
                        "  \"quantity\": 1,\n" +
                        "  \"shipDate\": \"2023-06-14T11:30:03.006Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
        .when()
                .post("/store/order")
        .then().log().all()
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_store_2")
    public void getPurchaseOrderByIDTest_ID_1(){
        given()
        .when()
                .get("/store/order/1")
        .then().log().all()
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_store_3")
    public void deletePurchaseOrderByIDTest_ID_1(){
        given()
        .when()
                .delete("/store/order/1")
        .then().log().all()
                .body("message", equalTo("1"))
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_store_4")
    public void returnsPetInventoriesByStatus(){
        given()
        .when()
                .get("/store/inventory")
        .then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    @DisplayName("api_test_user_1")
    public void createUsersWithArrayTest(){
        given()
                .contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
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
                        "    \"id\": 0,\n" +
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
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"user1\",\n" +
                        "  \"firstName\": \"John\",\n" +
                        "  \"lastName\": \"Smith\",\n" +
                        "  \"email\": \"JonhSmith@gmail.com\",\n" +
                        "  \"password\": \"1234\",\n" +
                        "  \"phone\": \"12345678\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
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
    public void createUserTest_UserId_2(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"user2\",\n" +
                        "  \"firstName\": \"Ron\",\n" +
                        "  \"lastName\": \"Wesley\",\n" +
                        "  \"email\": \"RonWesley@gmail.com\",\n" +
                        "  \"password\": \"4567\",\n" +
                        "  \"phone\": \"54947868\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
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


    @AfterAll
    public void afterAll() throws InterruptedException {
        LOGGER.info("Inside SwaggerPetStoreTests afterAll");
    }
    @AfterEach
    public void afterEach() {
        LOGGER.info("Inside SwaggerPetStoreTests afterEach ");
    }
}
