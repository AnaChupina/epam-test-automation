package com.epam.api.model;

public class Order extends BaseObject {
    private final Integer id;
    private final Integer petId;
    private final Integer quantity;
    private final String shipDate;
    private final String status;
    private final Boolean complete;
    public Order(Integer id, Integer petId, Integer quantity, String shipDate, String status, Boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPetId() {
        return petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getComplete() {
        return complete;
    }
    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"petId\": "+ petId + ",\n" +
                "  \"quantity\": " + quantity + ",\n" +
                "  \"shipDate\": \"" + shipDate + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\": " + complete + "\n" +
                "}";
    }
}
