package com.epam.api.utils;

import com.epam.api.builders.OrderBuilder;
import com.epam.api.model.Order;

import java.time.LocalDateTime;

public class OrderDataGenerator {
    protected static final Integer ORDER_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","order.id"));
    protected static final Integer PET_ID = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","pet.id"));
    protected static final Integer QUANTITY = Integer.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","quantity"));
    protected static final String SHIP_DATE = LocalDateTime.now().toString();
    protected static final String STATUS = FileHandler.getDataFromProperties("petstoretestdata.properties","status");
    protected static final Boolean COMPLETE = Boolean.valueOf(FileHandler.getDataFromProperties("petstoretestdata.properties","complete"));
    public static Order createOrder(){
        return new OrderBuilder()
                .setId(ORDER_ID)
                .setPetId(PET_ID)
                .setQuantity(QUANTITY)
                .setShipDate(SHIP_DATE)
                .setStatus(STATUS)
                .setComplete(COMPLETE)
                .build();
    }
}
