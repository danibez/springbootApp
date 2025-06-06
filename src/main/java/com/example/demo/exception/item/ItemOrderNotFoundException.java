package com.example.demo.exception.item;

public class ItemOrderNotFoundException extends RuntimeException {
    public ItemOrderNotFoundException(String message) {
        super(message);
    }
}
