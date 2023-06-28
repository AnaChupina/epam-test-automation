package com.epam.tests.api.swagger.petstore;

import com.epam.api.utils.OrderCreator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StoreTests {
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        RequestSpecification requestSpec = given()
                .contentType(ContentType.JSON)
                .body(OrderCreator.createJsonOrderObject());
        requestSpec.when()
                .post("/store/order");
        LOGGER.info("Order with ID=1 was created ");
    }
    @Test
    @DisplayName("api_test_store_1")
    public void placeOrderForPetTest(){
        given()
                .contentType (ContentType.JSON)
                .body(OrderCreator.createJsonOrderObject())
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

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerStoreTests afterEach ");
        RestAssured.reset();
    }
}
