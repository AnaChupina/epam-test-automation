package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.services.OrderHandler;
import com.epam.api.utils.OrderCreator;
import com.epam.api.utils.FileHandler;
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
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);
    private OrderHandler orderHandle;
    private static Response response;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @BeforeEach
    public void setUp() {
        String order = OrderCreator.createJsonOrderObject();
        orderHandle = new OrderHandler();
        response = orderHandle.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();;
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        LOGGER.info("Order with ID = 1 was created ");
    }
    @Test
    @DisplayName("api_test_store_3")
    public void deletePurchaseOrderByIDTest(){
        response = orderHandle.deletePurchaseOrderByID(orderId)
                .body("message", equalTo("1"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
}
