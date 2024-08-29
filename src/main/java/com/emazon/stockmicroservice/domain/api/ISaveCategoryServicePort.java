package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Category;


public interface ISaveCategoryServicePort {

    void saveCategory(Category category);
}
