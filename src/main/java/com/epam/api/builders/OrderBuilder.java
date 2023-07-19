package com.epam.api.builders;

import com.epam.api.model.Order;

public class OrderBuilder {
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
    public Order build() {
        if (id == null) {
            throw new IllegalArgumentException("ID must be set");
        }
        if(petId == null){
            throw new IllegalArgumentException("Pet ID must be set");
        }
        if(quantity == null){
            throw new IllegalArgumentException("Quantity must be set");
        }
        if(shipDate == null){
            throw new IllegalArgumentException("ShipDate must be set");
        }
        if(status == null){
            throw new IllegalArgumentException("Status must be set");
        }
        if(complete == null){
            throw new IllegalArgumentException("Complete must be set");
        }
        return new Order(id, petId, quantity, shipDate, status, complete);
    }
}
