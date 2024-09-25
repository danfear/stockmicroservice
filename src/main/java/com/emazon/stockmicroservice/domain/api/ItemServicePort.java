package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Item;

public interface ItemServicePort {
    void saveItem(Item item);
}
