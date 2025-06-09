package com.example.demo.exception.category;

public class InvalidCategoryNameException extends RuntimeException {
    public InvalidCategoryNameException(String message) {
        super(message);
    }
}
