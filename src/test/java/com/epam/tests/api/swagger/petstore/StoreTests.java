package com.epam.tests.api.swagger.petstore;

import com.epam.api.services.OrderHandle;
import com.epam.api.utils.OrderCreator;
import com.epam.api.utils.TestDataReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static com.epam.api.config.Configuration.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StoreTests {
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);
    private String order;
    private OrderHandle orderHandle;
    private static Response response;
    private final int orderId = Integer.valueOf(TestDataReader.getTestData("order.id"));

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        order = OrderCreator.createJsonOrderObject();
        orderHandle = new OrderHandle();
        response = orderHandle.placeOrderForPet(order);
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        LOGGER.info("Order with ID = 1 was created ");
    }
    @Test
    @DisplayName("api_test_store_1")
    public void placeOrderForPetTest(){
        response = orderHandle.placeOrderForPet(order);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_2")
    public void getPurchaseOrderByIDTest_ID_1(){
        response = orderHandle.getPurchaseOrderByID(orderId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_3")
    public void deletePurchaseOrderByIDTest_ID_1(){
        response = orderHandle.deletePurchaseOrderByID(orderId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_4")
    public void returnsPetInventoriesByStatus(){
        response = orderHandle.returnsPetInventoriesByStatus();
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerStoreTests afterEach ");
        RestAssured.reset();
    }
}
