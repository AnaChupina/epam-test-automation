package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.services.OrderHandler;
import com.epam.api.utils.OrderCreator;
import com.epam.api.utils.FileHandler;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class StoreTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);
    private String order;
    private OrderHandler orderHandle;
    private static Response response;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @BeforeEach
    public void setUp() {
        order = OrderCreator.createJsonOrderObject();
        orderHandle = new OrderHandler();
        response = orderHandle.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        LOGGER.info("Order with ID = 1 was created ");
    }
    @Test
    @DisplayName("api_test_store_1")
    public void placeOrderForPetTest(){
        response = orderHandle.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_2")
    public void getPurchaseOrderByIDTest(){
        response = orderHandle.getPurchaseOrderByID(orderId)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_4")
    public void returnsPetInventoriesByStatus(){
        response = orderHandle.returnsPetInventoriesByStatus()
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerStoreTests afterEach ");
        response = orderHandle.deletePurchaseOrderByID(orderId)
                .body("message", equalTo("1"))
                .extract().response();;

    }
}
