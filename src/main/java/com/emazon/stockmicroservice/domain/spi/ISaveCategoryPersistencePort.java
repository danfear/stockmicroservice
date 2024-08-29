package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Category;

public interface ISaveCategoryPersistencePort {

    void saveCategory(Category category);
}
