package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Item;

public interface ItemPersistencePort {
    void saveItem(Item item);
    boolean existsByName(String name);
}
