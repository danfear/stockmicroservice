package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;

import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockmicroservice.application.mapper.ICategoryResponseMapper;
import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.util.Pagination;
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
    public PaginatedResponse<CategoryResponse> getAllCategoriesFromStock(Pagination pagination) {
        List<Category> categories = categoryServicePort.getAllCategories(pagination);
        List<CategoryResponse> categoryResponses = categoryResponseMapper.toResponseList(categories);

        long totalElements = categoryServicePort.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalElements / pagination.getSize());

        return new PaginatedResponse<>(
                categoryResponses,
                pagination.getPage(),
                pagination.getSize(),
                pagination.getSortBy(),
                pagination.isAscending(),
                totalElements,
                totalPages
        );
    }
}
