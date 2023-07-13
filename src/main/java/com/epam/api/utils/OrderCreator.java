package com.epam.api.utils;

import com.epam.api.builders.OrderBuilder;
import com.epam.api.model.Order;
import com.google.gson.Gson;

import static com.epam.api.utils.OrderDataGenerator.*;


public class OrderCreator {

    public static String createJsonOrderObject(){
        Order order = new OrderBuilder()
                .setId(ORDER_ID)
                .setPetId(PET_ID)
                .setQuantity(QUANTITY)
                .setShipDate(SHIP_DATE)
                .setStatus(STATUS)
                .setComplete(COMPLETE)
                .build();
        return new Gson().toJson(order);
    }
}
