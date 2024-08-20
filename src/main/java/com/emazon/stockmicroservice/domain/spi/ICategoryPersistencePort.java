package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

}
