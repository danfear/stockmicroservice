package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.ItemRequest;
import com.emazon.stockmicroservice.application.handler.IItemHandler;
import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRestController {

    private final IItemHandler itemHandler;

    @PostMapping
    public ResponseEntity<Void> saveItemInStock(@RequestBody ItemRequest itemRequest) {
        itemHandler.saveItemInStock(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<Item>> getAllItemsFromStock(Pagination paginationRequest) {
        PaginatedResponse<Item> items = itemHandler.getAllItems(paginationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}
