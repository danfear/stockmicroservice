package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;

public interface ItemPersistencePort {
    void saveItem(Item item);
    boolean existsByName(String name);
    PaginatedResponse<Item> getAllItems(Pagination paginationRequest);

    long getTotalElements();
}
