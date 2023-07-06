package com.epam.api.utils;

import com.epam.api.model.Order;
import com.google.gson.Gson;

import java.time.LocalDateTime;


public class OrderCreator {
    private static final Integer ORDER_ID = Integer.valueOf(TestDataReader.getTestData("order.id"));
    private static final Integer PET_ID = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    private static final Integer QUANTITY = Integer.valueOf(TestDataReader.getTestData("quantity"));
    private static final String SHIP_DATE = LocalDateTime.now().toString();
    private static final String STATUS = TestDataReader.getTestData("status");
    private static final Boolean COMPLETE = Boolean.valueOf(TestDataReader.getTestData("complete"));

    public static String createJsonOrderObject(){
        Order order = new Order.OrderBuilder(ORDER_ID, PET_ID, QUANTITY, SHIP_DATE, STATUS, COMPLETE)
                .build();
        return new Gson().toJson(order);
    }
}
