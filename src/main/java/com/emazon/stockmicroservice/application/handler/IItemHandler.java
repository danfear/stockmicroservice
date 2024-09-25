package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.ItemRequest;

public interface IItemHandler {
void saveItemInStock(ItemRequest itemRequest);
}
