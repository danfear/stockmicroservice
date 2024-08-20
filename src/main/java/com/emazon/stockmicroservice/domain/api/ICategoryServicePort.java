package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Category;

public interface ICategoryServicePort {

    void saveCategory(Category category);

}
