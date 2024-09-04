package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    List<Category> getAllCategories();
}
