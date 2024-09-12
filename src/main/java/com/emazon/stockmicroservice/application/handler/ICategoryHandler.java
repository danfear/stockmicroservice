package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;

public interface ICategoryHandler {
    void saveCategoryInStock(CategoryRequest categoryRequest);
    PaginatedResponse<CategoryResponse> getAllCategoriesFromStock(Pagination pagination);

}
