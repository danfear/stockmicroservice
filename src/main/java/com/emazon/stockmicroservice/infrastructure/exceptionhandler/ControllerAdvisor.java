package com.emazon.stockmicroservice.infrastructure.exceptionhandler;

import com.emazon.stockmicroservice.domain.exception.brandexceptions.BrandAlreadyExistsException;
import com.emazon.stockmicroservice.domain.exception.categoryexceptions.CategoryAlreadyExistsException;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidPageException;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidSizeException;
import com.emazon.stockmicroservice.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS
                        .getMessage()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandAlreadyExistsException brandAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_ALREADY_EXISTS
                        .getMessage()));
    }

    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageException(InvalidPageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(InvalidSizeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSizeException(InvalidSizeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleItemAlreadyExistsException(
            ItemAlreadyExistsException itemAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ITEM_ALREADY_EXISTS
                        .getMessage()));
    }

}
