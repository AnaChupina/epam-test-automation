package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.services.OrderHandle;
import com.epam.api.utils.OrderCreator;
import com.epam.api.utils.TestDataReader;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epam.api.config.Configuration.STATUS_CODE;

public class DeleteOrderTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);
    private OrderHandle orderHandle;
    private static Response response;
    private final int orderId = Integer.valueOf(TestDataReader.getTestData("order.id"));

    @BeforeEach
    public void setUp() {
        String order = OrderCreator.createJsonOrderObject();
        orderHandle = new OrderHandle();
        response = orderHandle.placeOrderForPet(order);
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        LOGGER.info("Order with ID = 1 was created ");
    }
    @Test
    @DisplayName("api_test_store_3")
    public void deletePurchaseOrderByIDTest(){
        response = orderHandle.deletePurchaseOrderByID(orderId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
}
