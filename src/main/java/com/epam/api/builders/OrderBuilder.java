package com.epam.api.builders;

import com.epam.api.exception.OrderIsNotReadyToBuildException;
import com.epam.api.model.Order;
import com.epam.api.utils.OrderDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderBuilder {
    private static final Logger LOGGER = LogManager.getLogger(OrderDataGenerator.class);
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public OrderBuilder setId(Integer id) {
        this.id = id;
        return this;
    }
    public OrderBuilder setPetId(Integer petId) {
        this.petId = petId;
        return this;
    }
    public OrderBuilder setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    public OrderBuilder setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setComplete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    @Override
    public String toString() {
        return "OrderBuilder{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status='" + status + '\'' +
                ", complete=" + complete +
                '}';
    }
    public Order build() throws OrderIsNotReadyToBuildException {
        if(id != null && petId != null && quantity != null &&  status != null && complete != null){
            return new Order(id, petId, quantity, shipDate, status, complete);
        } else {
            throw new OrderIsNotReadyToBuildException("Some data of the order is null! Check testdata!");
        }
    }
    public Order buildOrderSafely(Integer orderId, Integer petId, Integer quantity, String shipDate, String status, Boolean complete){
        Order order = null;
        try{
             order = new OrderBuilder()
                    .setId(orderId)
                    .setPetId(petId)
                    .setQuantity(quantity)
                    .setShipDate(shipDate)
                    .setStatus(status)
                    .setComplete(complete)
                     .build();
        }catch (OrderIsNotReadyToBuildException exp){
            LOGGER.fatal(exp);
        }
        return order;
    }
}
