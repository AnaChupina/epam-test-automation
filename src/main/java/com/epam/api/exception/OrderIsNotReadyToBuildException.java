package com.epam.api.exception;

public class OrderIsNotReadyToBuildException extends Exception{
    public OrderIsNotReadyToBuildException(String message) {
        super(message);
    }
}
