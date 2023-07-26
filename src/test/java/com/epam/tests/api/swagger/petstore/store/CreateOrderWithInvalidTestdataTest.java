package com.epam.tests.api.swagger.petstore.store;

import com.epam.api.exception.OrderIsNotReadyToBuildException;
import com.epam.api.utils.ObjectToJsonConvertor;
import com.epam.api.utils.OrderDataGenerator;
import com.epam.tests.base.BaseAPITest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateOrderWithInvalidTestdataTest extends BaseAPITest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteOrderTest.class);
    private String order;

    @Test
    public void TestOrderIsNotReadyToBuildException(){
        OrderIsNotReadyToBuildException thrown = Assertions.assertThrows(OrderIsNotReadyToBuildException.class, () -> {
            order = ObjectToJsonConvertor.convertObjectToJson(OrderDataGenerator.createOrder(null,null,null,null, null, null));
            LOGGER.info("JSON request with order was created");
            LOGGER.info(order);
        });

        Assertions.assertEquals("Some data of the order is null! Check testdata!", thrown.getMessage());
    }

}
