package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.ICategoryHandler;
import com.emazon.stockmicroservice.domain.util.Pagination;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Pagination pagination = new Pagination(page, size, sortBy, ascending);
        PaginatedResponse<CategoryResponse> paginatedResponse = categoryHandler.getAllCategoriesFromStock(pagination);
        return ResponseEntity.ok(paginatedResponse);
    }
}
