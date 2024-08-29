package com.emazon.stockmicroservice.application.handler.categoryhandler;


import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.mapper.ICategoryRequestMapper;
import com.emazon.stockmicroservice.domain.api.ISaveCategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SaveCategoryHandlerTest {

    @InjectMocks
    private SaveCategoryHandler saveCategoryHandler;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ISaveCategoryServicePort categoryServicePort;

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

        Category category = new Category(50L,"Electronics", "Devices");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        // When
        saveCategoryHandler.saveCategoryInStock(categoryRequest);

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

        Category category = new Category(50L,"Electronics", "Devices");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doThrow(new RuntimeException("Save failed")).when(categoryServicePort).saveCategory(category);

        // When & Then
        try {
            saveCategoryHandler.saveCategoryInStock(categoryRequest);
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }
}