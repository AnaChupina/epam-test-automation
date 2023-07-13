package com.epam.api.utils;

import java.time.LocalDateTime;

public class OrderDataGenerator {
    protected static final Integer ORDER_ID = Integer.valueOf(TestDataReader.getTestData("order.id"));
    protected static final Integer PET_ID = Integer.valueOf(TestDataReader.getTestData("pet.id"));
    protected static final Integer QUANTITY = Integer.valueOf(TestDataReader.getTestData("quantity"));
    protected static final String SHIP_DATE = LocalDateTime.now().toString();
    protected static final String STATUS = TestDataReader.getTestData("status");
    protected static final Boolean COMPLETE = Boolean.valueOf(TestDataReader.getTestData("complete"));
}
