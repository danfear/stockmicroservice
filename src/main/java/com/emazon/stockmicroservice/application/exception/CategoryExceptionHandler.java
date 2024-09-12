package com.emazon.stockmicroservice.application.exception;

import com.emazon.stockmicroservice.domain.exception.categoryexceptions.DescriptionEmptyException;
import com.emazon.stockmicroservice.domain.exception.categoryexceptions.DescriptionOversizedException;
import com.emazon.stockmicroservice.domain.exception.categoryexceptions.NameEmptyException;
import com.emazon.stockmicroservice.domain.exception.categoryexceptions.NameOversizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CategoryExceptionHandler {
    static final String VALIDATION_ERROR = "Validation Error";

    @ExceptionHandler(NameEmptyException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNameEmptyException(NameEmptyException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DescriptionEmptyException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDescriptionEmptyException(DescriptionEmptyException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameOversizedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNameOversizedException(NameOversizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DescriptionOversizedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDescriptionOversizedException(DescriptionOversizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_ERROR,
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
