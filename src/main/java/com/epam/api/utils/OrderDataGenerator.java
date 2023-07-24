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
    public static Order createOrder(){
        OrderBuilder builder = new OrderBuilder()
                .setId(ORDER_ID)
                .setPetId(PET_ID)
                .setQuantity(QUANTITY)
                .setShipDate(SHIP_DATE)
                .setStatus(STATUS)
                .setComplete(COMPLETE);
        try {
            return builder.build();
        }catch (OrderIsNotReadyToBuildException exp){
            LOGGER.fatal(exp);
        }finally {
            LOGGER.info(builder.toString());
        }
        return null;
    }
    public static Order createOrderWithInvalidData(Integer iD){
        OrderBuilder builder = new OrderBuilder()
                .setId(iD)
                .setPetId(PET_ID)
                .setQuantity(QUANTITY)
                .setShipDate(SHIP_DATE)
                .setStatus(STATUS)
                .setComplete(COMPLETE);
        try {
            return builder.build();
        }catch (OrderIsNotReadyToBuildException exp){
            LOGGER.fatal(exp);
        }finally {
            LOGGER.info(builder.toString());
        }
        return null;
    }
}
