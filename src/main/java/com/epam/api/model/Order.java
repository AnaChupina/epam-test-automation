package com.epam.api.model;

public class Order {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.petId = builder.petId;
        this.quantity = builder.quantity;
        this.shipDate = builder.shipDate;
        this.status = builder.status;
        this.complete = builder.complete;
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
    public static class OrderBuilder{
        private Integer id;
        private Integer petId;
        private Integer quantity;
        private String shipDate;
        private String status;
        private Boolean complete;

        public OrderBuilder(Integer id, Integer petId, Integer quantity, String shipDate, String status, Boolean complete) {
            this.id = id;
            this.petId = petId;
            this.quantity = quantity;
            this.shipDate = shipDate;
            this.status = status;
            this.complete = complete;
        }

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
            return new Order(this);
        }
    }
}
