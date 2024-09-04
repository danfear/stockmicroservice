package com.emazon.stockmicroservice.application.handler.categoryhandler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;

import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockmicroservice.application.mapper.ICategoryResponseMapper;
import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategoryInStock(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategoriesFromStock() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories());
    }
}
