package com.example.demo.exception.item;

public class InvalidItemQuantityException extends RuntimeException {
    public InvalidItemQuantityException(String message) {
        super(message);
    }
}
