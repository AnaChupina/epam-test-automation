package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.exception.OrderIsNotReadyToBuildException;
import com.epam.api.utils.FileHandler;
import com.epam.api.utils.ObjectToJsonConvertor;
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

public class DeleteOrderWithInvalidTestdataTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteOrderTest.class);
    private static Response response;
    private String order;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @Test
    public void createOrderWithInvalidData(){

        OrderIsNotReadyToBuildException thrown = Assertions.assertThrows(OrderIsNotReadyToBuildException.class, () -> {
            order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrder(null,null,null,null, null, null));
            LOGGER.info("JSON request with order was created");
            LOGGER.info(order);
        });
        Assertions.assertEquals("Some data of the order is null! Check testdata!", thrown.getMessage());
        response = orderHandler.placeOrderForPet(order)
                .body("type", equalTo("error"))
                .body("message", equalTo("No data"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_BAD_REQUEST,response.statusCode());
        LOGGER.info("Request to place the order was sent to the server");
        LOGGER.debug(response.asString());
    }



//    @BeforeEach
//    public void setUp() {
//        String order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrder(null,null,null,null, null, null));
//        LOGGER.info("JSON request with order was created");
//        LOGGER.info(order);
//        response = orderHandler.placeOrderForPet(order)
//                .body("type", equalTo("error"))
//                .body("message", equalTo("No data"))
//                .extract().response();
//        Assertions.assertEquals(HttpURLConnection.HTTP_BAD_REQUEST,response.statusCode());
//        LOGGER.info("Request to place the order was sent to the server");
//        LOGGER.debug(response.asString());
//    }
//    @Test
//    @DisplayName("Delete order by ID = 1. But this order must be not found because createOrderWithInvalidData(null) was used in setUp()")
//    public void deletePurchaseOrderByIdTest(){
//        response = orderHandler.deletePurchaseOrderByID(orderId)
//                .extract().response();
//        LOGGER.info("Request to delete the order was sent and server response was received");
//        LOGGER.debug(response.asString());
//        Assertions.assertEquals(HttpURLConnection.HTTP_NOT_FOUND,response.statusCode());
//    }

}
