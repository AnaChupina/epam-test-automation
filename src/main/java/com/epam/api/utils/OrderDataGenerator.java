package com.epam.api.utils;

import com.epam.api.builders.OrderBuilder;
import com.epam.api.exception.OrderIsNotReadyToBuildException;
import com.epam.api.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class OrderDataGenerator {
    private static final Logger LOGGER = LogManager.getLogger(OrderDataGenerator.class);
    protected static final Integer ORDER_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));
    protected static final Integer PET_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    protected static final Integer QUANTITY = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","quantity"));
    protected static final String SHIP_DATE = LocalDateTime.now().toString();
    protected static final String STATUS = FileHandler.getDataFromProperties("petstoretestdata.properties","status");
    protected static final Boolean COMPLETE = Boolean.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","complete"));

    public static Order createOrderWithTestData(){
        Order order = new OrderBuilder()
                .buildOrderSafely(ORDER_ID, PET_ID, QUANTITY, SHIP_DATE, STATUS, COMPLETE);
        return order;
    }

    public static Order createOrder(Integer orderId, Integer petId, Integer quantity, String shipDate, String status, Boolean complete) throws OrderIsNotReadyToBuildException {
        Order order = new OrderBuilder()
                .setId(orderId)
                .setPetId(petId)
                .setQuantity(quantity)
                .setShipDate(shipDate)
                .setStatus(status)
                .setComplete(complete)
                .build();
        return order;
    }

}
