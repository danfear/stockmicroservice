package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Category;

import java.util.List;


public interface ICategoryServicePort {
    void saveCategory(Category category);
    List<Category> getAllCategories();
}
