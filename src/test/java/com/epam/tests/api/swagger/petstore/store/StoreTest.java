package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.FileHandler;
import com.epam.api.utils.OrderDataGenerator;
import com.epam.tests.base.BaseAPITest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;

public class StoreTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(StoreTest.class);
    private String order;
    private static Response response;
    private final int orderId = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));

    @BeforeEach
    public void setUp() {
        order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrder());
        response = orderHandler.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
        LOGGER.info("Inside SwaggerStoreTests beforeEach ");
        LOGGER.info("Order with ID = 1 was created ");
    }
    @Test
    @DisplayName("Place an order for pet")
    public void placeOrderForPetTest(){
        response = orderHandler.placeOrderForPet(order)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Find purchase order by ID")
    public void getPurchaseOrderByIDTest(){
        response = orderHandler.getPurchaseOrderByID(orderId)
                .body("complete", equalTo(true))
                .body("status", equalTo("placed"))
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }
    @Test
    @DisplayName("Returns pet inventories by status")
    public void returnsPetInventoriesByStatus(){
        response = orderHandler.returnsPetInventoriesByStatus()
                .extract().response();;
        Assertions.assertEquals(HttpURLConnection.HTTP_OK,response.statusCode());
    }

    @AfterEach
    public void cleanUp() {
        LOGGER.info("Inside SwaggerStoreTests afterEach ");
        response = orderHandler.deletePurchaseOrderByID(orderId)
                .body("message", equalTo("1"))
                .extract().response();;

    }
}
