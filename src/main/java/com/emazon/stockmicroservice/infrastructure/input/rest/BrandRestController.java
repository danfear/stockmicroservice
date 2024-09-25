package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.IBrandHandler;
import com.emazon.stockmicroservice.application.handler.ICategoryHandler;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidPageException;
import com.emazon.stockmicroservice.infrastructure.exception.InvalidSizeException;
import com.emazon.stockmicroservice.infrastructure.util.RestControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;
    private final ICategoryHandler categoryHandler;

    @PostMapping()
    public ResponseEntity<Void> saveBrandInStock(@RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrandInStock(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<PaginatedResponse<BrandResponse>> getAllBrandsFromStock(
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
        PaginatedResponse<BrandResponse> paginatedResponse = brandHandler.getAllBrandsFromStock(pagination);
        return ResponseEntity.ok(paginatedResponse);
    }
}
