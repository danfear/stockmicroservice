package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.ItemRequest;
import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;

public interface IItemHandler {
void saveItemInStock(ItemRequest itemRequest);
PaginatedResponse<Item> getAllItems(Pagination paginationRequest);
}
