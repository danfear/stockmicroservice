package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.handler.categoryhandler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategoryInStock(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategoryInStock(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesFromStock(){
        return ResponseEntity.ok(categoryHandler.getAllCategoriesFromStock());
    }
}
