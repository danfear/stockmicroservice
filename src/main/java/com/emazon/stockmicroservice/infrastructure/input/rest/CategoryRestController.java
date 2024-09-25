package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.ICategoryHandler;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidPageException;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidSizeException;
import com.emazon.stockmicroservice.infrastructure.util.RestControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @PostMapping()
    public ResponseEntity<Void> saveCategoryInStock(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategoryInStock(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<PaginatedResponse<CategoryResponse>> getAllCategoriesFromStock(
            @RequestParam(defaultValue = RestControllerConstants.PAGE_DEFAULT_VALUE) int page,
            @RequestParam(defaultValue = RestControllerConstants.SIZE_DEFAULT_VALUE) int size,
            @RequestParam(defaultValue = RestControllerConstants.SORT_BY_DEFAULT_VALUE) String sortBy,
            @RequestParam(defaultValue = RestControllerConstants.ASCENDING_DEFAULT_VALUE) boolean ascending) {
        if (page < 0) {
            throw new InvalidPageException("Page number cannot be negative");
        }
        if (size <= 0) {
            throw new InvalidSizeException("Size must be greater than zero");
        }

        Pagination pagination = new Pagination(page, size, sortBy, ascending);
        PaginatedResponse<CategoryResponse> paginatedResponse = categoryHandler.getAllCategoriesFromStock(pagination);
        return ResponseEntity.ok(paginatedResponse);
    }
}
