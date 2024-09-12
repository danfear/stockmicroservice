package com.emazon.stockmicroservice.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("There is already a category with that name"),
    BRAND_ALREADY_EXISTS("There is already a category with that name"),
    NO_DATA_FOUND("No data found for the requested petition");

    private String message;

    ExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
