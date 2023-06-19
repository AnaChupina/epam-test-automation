package com.epam.utils.api;

import com.epam.model.swagger.petstore.Category;
import com.epam.model.swagger.petstore.Order;
import com.epam.model.swagger.petstore.Pet;
import com.epam.model.swagger.petstore.Tag;

import java.util.Arrays;
import java.util.List;

public class OrderCreator {
    private static final Integer ORDER_ID = Integer.valueOf(TestDataReader.getTestData("order.id"));
    private static final Integer PET_ID = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    private static final Integer QUANTITY = Integer.valueOf(TestDataReader.getTestData("quantity"));
    private static final String SHIP_DATE = TestDataReader.getTestData("ship.date");
    private static final String STATUS = TestDataReader.getTestData("status");
    private static final Boolean COMPLETE = Boolean.valueOf(TestDataReader.getTestData("complete"));

    public static String createOrder(){
        Order order = new Order(ORDER_ID, PET_ID, QUANTITY, SHIP_DATE, STATUS, COMPLETE);
        return order.toString();
    }
}
