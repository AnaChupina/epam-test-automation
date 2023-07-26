package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.utils.FileHandler;
import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.OrderDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(StoreTest.class);
    private String order;
    private static Response response;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @Test
    @DisplayName("Place an order for pet")
    public void placeOrderForPetTest(){
        order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrderWithTestData());
        LOGGER.info("JSON request with order was created");
        LOGGER.info(order);
        response = orderHandler.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
        LOGGER.info("Request to place the order was sent to the server");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerStoreTests afterEach ");
        response = orderHandler.deletePurchaseOrderByID(orderId)
                .body("message", equalTo("1"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
        LOGGER.info("Request to delete the order was sent and server response was received");
        LOGGER.debug(response.asString());
    }
}
