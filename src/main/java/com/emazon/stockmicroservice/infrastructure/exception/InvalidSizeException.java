package com.emazon.stockmicroservice.infrastructure.exception;

public class InvalidSizeException extends RuntimeException{
    public InvalidSizeException(String message) {
        super(message);
    }
}
