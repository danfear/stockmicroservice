package com.emazon.stockmicroservice.application.exception;

import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemCategoryMaximumCountException;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemCategoryMinimumCountException;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemCategoryNotFoundException;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemCategoryUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ItemExceptionHandler {
    static final String VALIDATION_ERROR = "Validation Error";
    @ExceptionHandler(ItemCategoryMinimumCountException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleItemCategoryMinimumCountException(ItemCategoryMinimumCountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemCategoryMaximumCountException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleItemCategoryMaximumCountException(ItemCategoryMaximumCountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemCategoryUniqueException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleItemCategoryUniqueException(ItemCategoryUniqueException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemCategoryNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleItemCategoryNotFoundException(ItemCategoryNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
