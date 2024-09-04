package com.emazon.stockmicroservice.application.handler.categoryhandler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    void saveCategoryInStock(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategoriesFromStock();

}
