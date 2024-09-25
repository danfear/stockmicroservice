package com.emazon.stockmicroservice.infrastructure.exception;

public class InvalidPageException extends RuntimeException{
    public InvalidPageException(String message) {
        super(message);
    }
}
