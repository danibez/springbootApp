package com.example.demo.exception.seller;

public class InvalidCnpjException extends RuntimeException {
    public InvalidCnpjException(String message) {
        super(message);
    }
}
