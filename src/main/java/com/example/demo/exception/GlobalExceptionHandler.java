package com.example.demo.exception;

import com.example.demo.exception.category.CategoryNotFoundException;
import com.example.demo.exception.category.InvalidCategoryNameException;
import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.exception.client.CpfAlreadyExistsException;
import com.example.demo.exception.client.InvalidCpfException;
import com.example.demo.exception.item.InvalidItemQuantityException;
import com.example.demo.exception.order.EmptyOrderException;
import com.example.demo.exception.order.InvalidOrderStatusException;
import com.example.demo.exception.order.OrderAlreadyPaidException;
import com.example.demo.exception.order.OrderNotFoundException;
import com.example.demo.exception.payment.InsufficientPaymentException;
import com.example.demo.exception.payment.InvalidPaymentStatusException;
import com.example.demo.exception.payment.PaymentNotFoundException;
import com.example.demo.exception.product.InvalidProductNameException;
import com.example.demo.exception.product.InvalidProductPriceException;
import com.example.demo.exception.product.ProductNotFoundException;
import com.example.demo.exception.seller.CnpjAlreadyExistsException;
import com.example.demo.exception.seller.InvalidCnpjException;
import com.example.demo.exception.seller.SellerHasActiveProductsException;
import com.example.demo.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            CpfAlreadyExistsException.class,
            CnpjAlreadyExistsException.class,
            CategoryNotFoundException.class,
            ProductNotFoundException.class,
            OrderNotFoundException.class,
            PaymentNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            InvalidEmailException.class,
            InvalidPasswordException.class,
            InvalidPhoneException.class,
            InvalidCpfException.class,
            InvalidCnpjException.class,
            SellerHasActiveProductsException.class,
            InvalidCategoryNameException.class,
            InvalidProductNameException.class,
            InvalidProductPriceException.class,
            ClientNotFoundException.class,
            EmptyOrderException.class,
            InvalidOrderStatusException.class,
            OrderAlreadyPaidException.class,
            InvalidItemQuantityException.class,
            InvalidPaymentStatusException.class,
            InsufficientPaymentException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    public static class ErrorResponse {
        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
