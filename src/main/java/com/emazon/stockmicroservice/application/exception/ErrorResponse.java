package com.emazon.stockmicroservice.application.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorType;
    private String message;

    public ErrorResponse(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }
}
