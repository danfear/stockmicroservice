package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockmicroservice.application.mapper.ICategoryResponseMapper;
import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.util.Pagination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

class CategoryHandlerTest {
    @InjectMocks
    private CategoryHandler categoryHandler;
    @Mock
    private ICategoryRequestMapper categoryRequestMapper;
    @Mock
    private ICategoryServicePort categoryServicePort;
    @Mock
    private ICategoryResponseMapper categoryResponseMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_CategoryResquestIsCorrect_Expect_SaveCategoryInStockSuccess() {
        // Given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        Category category = new Category(50L, "Electronics", "Devices");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        // When
        categoryHandler.saveCategoryInStock(categoryRequest);

        // Then
        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }

    @Test
    void when_CategoryResquestIsCorrect_Expect_SaveCategoryInStockFailure() {
        // Given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        Category category = new Category(50L, "Electronics", "Devices");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doThrow(new RuntimeException("Save failed")).when(categoryServicePort).saveCategory(category);

        // When & Then
        try {
            categoryHandler.saveCategoryInStock(categoryRequest);
            categoryHandler.saveCategoryInStock(categoryRequest);
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }

    @Test
    void when_ParametersAreCorrect_Expect_GetAllCategoriesFromStockSuccessfully() {
        Pagination pagination = new Pagination(0, 10, "name", true);
        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various books");

        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponse response1 = new CategoryResponse();
        response1.setName("Electronics");
        response1.setDescription("Devices and gadgets");

        CategoryResponse response2 = new CategoryResponse();
        response2.setName("Books");
        response2.setDescription("Various books");

        List<CategoryResponse> responses = Arrays.asList(response1, response2);

        when(categoryServicePort.getAllCategories(pagination)).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(responses);
        when(categoryServicePort.getTotalElements()).thenReturn(2L);

        PaginatedResponse<CategoryResponse> result = categoryHandler.getAllCategoriesFromStock(pagination);

        assertEquals(2, result.getData().size());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals("name", result.getSortBy());
        assertTrue(result.isAscending());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }
}