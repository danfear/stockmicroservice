package com.emazon.stockmicroservice.application.handler.categoryhandler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;

import com.emazon.stockmicroservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveCategoryHandler implements ISaveCategoryHandler{

    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public void saveCategoryInStock(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }
}
