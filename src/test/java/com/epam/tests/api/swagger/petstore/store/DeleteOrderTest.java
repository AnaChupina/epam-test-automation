package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.FileHandler;
import com.epam.api.utils.OrderDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;


public class DeleteOrderTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteOrderTest.class);
    private static Response response;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @BeforeEach
    public void setUp() {
        String order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrder());
        LOGGER.info("JSON request with order was created");
        LOGGER.info(order);
        response = orderHandler.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();;
        LOGGER.info("Request to place the order was sent to the server");
        LOGGER.debug(response.asString());
    }
    @Test
    @DisplayName("Delete purchase order by order ID=1")
    public void deletePurchaseOrderByIdTest(){
        response = orderHandler.deletePurchaseOrderByID(orderId)
                .body("message", equalTo("1"))
                .extract().response();
        LOGGER.info("Request to delete the order was sent and server response was received");
        LOGGER.debug(response.asString());
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
