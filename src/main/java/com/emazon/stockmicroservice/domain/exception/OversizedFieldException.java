package com.emazon.stockmicroservice.domain.exception;

public class OversizedFieldException extends RuntimeException{

    public OversizedFieldException(String message) {
        super(message);
    }
}
