package com.epam.tests.api.swagger.petstore;

import com.epam.api.services.OrderHandle;
import com.epam.api.utils.OrderCreator;
import com.epam.api.utils.TestDataReader;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static com.epam.api.config.Configuration.*;
public class StoreTests extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(StoreTests.class);
    private String order;
    private OrderHandle orderHandle;
    private static Response response;
    private final int orderId = Integer.valueOf(TestDataReader.getTestData("order.id"));
    private Boolean deleteTestFlag = false;

    @BeforeEach
    public void setUp() {
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
    public void getPurchaseOrderByIDTest(){
        response = orderHandle.getPurchaseOrderByID(orderId);
        Assertions.assertEquals(STATUS_CODE,response.statusCode());
    }
    @Test
    @DisplayName("api_test_store_3")
    public void deletePurchaseOrderByIDTest(){
        deleteTestFlag = true;
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
        if (!deleteTestFlag){
            response = orderHandle.deletePurchaseOrderByID(orderId);
        }
    }
}