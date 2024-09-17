package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.IBrandHandler;
import com.emazon.stockmicroservice.domain.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @PostMapping()
    public ResponseEntity<Void> saveBrandInStock(@RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrandInStock(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<PaginatedResponse<BrandResponse>> getAllBrandsFromStock(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Pagination pagination = new Pagination(page, size, sortBy, ascending);
        PaginatedResponse<BrandResponse> paginatedResponse = brandHandler.getAllBrandsFromStock(pagination);
        return ResponseEntity.ok(paginatedResponse);
    }

}
