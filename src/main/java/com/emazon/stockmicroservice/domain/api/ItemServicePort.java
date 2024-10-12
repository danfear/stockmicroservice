package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;

public interface ItemServicePort {
    void saveItem(Item item);
    PaginatedResponse<Item> getAllItems(Pagination paginationRequest);

}
