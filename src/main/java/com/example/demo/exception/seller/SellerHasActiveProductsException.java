package com.example.demo.exception.seller;

public class SellerHasActiveProductsException extends RuntimeException {
    public SellerHasActiveProductsException(String message) {
        super(message);
    }
}
