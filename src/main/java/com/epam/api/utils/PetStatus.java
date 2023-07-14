package com.epam.api.utils;

public enum PetStatus {
    AVAILABLE ("available"),
    PENDING ("pending"),
    SOLD ("sold");
    private final String status;
    PetStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
