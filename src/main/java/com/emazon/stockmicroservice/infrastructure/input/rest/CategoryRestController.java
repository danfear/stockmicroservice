package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.handler.categoryhandler.ISaveCategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ISaveCategoryHandler saveCategoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategoryInStock(@RequestBody CategoryRequest categoryRequest) {
        saveCategoryHandler.saveCategoryInStock(categoryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
