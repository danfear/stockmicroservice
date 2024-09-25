package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.ItemRequest;
import com.emazon.stockmicroservice.application.handler.IItemHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
