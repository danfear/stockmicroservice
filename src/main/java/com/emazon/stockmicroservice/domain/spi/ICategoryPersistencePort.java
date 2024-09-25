package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.util.Pagination;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    boolean existsByName(String name);
    List<Category> getAllCategories(Pagination pagination);
    long getTotalElements();

    List<Long> findExistingCategoriesByIds(List<Long> categoryIds);
}
