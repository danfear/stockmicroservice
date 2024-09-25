package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.exception.categoryexceptions.*;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;

import com.emazon.stockmicroservice.domain.util.Pagination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void when_CategoryInformationIsCorrect_Expect_CategoryToBeSavedSuccessfully() {
        //Given
        Category category = new Category(50L,"Electronicos", "Productos electronicos");
        //When
        this.categoryUseCase.saveCategory(category);
        //Then
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(this.categoryPersistencePort).saveCategory(categoryArgumentCaptor.capture());
        assertEquals(50L,categoryArgumentCaptor.getValue().getId());
        assertEquals("Electronicos",categoryArgumentCaptor.getValue().getName());
        assertEquals("Productos electronicos",categoryArgumentCaptor.getValue().getDescription());
    }

    @Test
    void when_CategoryNameIsEmpty_Expect_NameEmptyException() {
        //Given
        Category category = new Category(50L,"", "Productos electronicos");
        //Then
        assertThrows(NameEmptyException.class, () -> {
            categoryUseCase.saveCategory(category);
        });
    }

    @Test
    void when_CategoryNameExists_Expect_ThrowCategoryAlreadyExistsException() {
        // Given
        Category category = new Category(10L,"Electronics", "Devices and gadgets");
        // When
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(true);
        // Then
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryUseCase.saveCategory(category);
        });
        // Verify that the persistence port was called
        Mockito.verify(categoryPersistencePort).existsByName(category.getName());
    }

    @Test
    void when_CategoryDescriptionIsEmpty_Expect_DescriptionEmptyException() {
        //Given
        Category category = new Category(50L,"Electronicos", "");
        //Then
        assertThrows(DescriptionEmptyException.class, () -> {
            categoryUseCase.saveCategory(category);
        });
    }

    @Test
    void when_CategoryNameIsOverMaxLength_Expect_NameOversizedException() {
        //Given
        Category category = new Category(50L,"Electronicossssssssssssssssssssssssssssssssssssssss",
                "Productos electronicos");
        //Then
        assertThrows(NameOversizedException.class, () -> {
            categoryUseCase.saveCategory(category);
        });
    }

    @Test
    void when_CategoryDescriptionIsOverMaxLength_Expect_DescriptionOversizedException() {
        //Given
        Category category = new Category(50L,"Electronicos",
                "Productos electronicossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        //Then
        assertThrows(DescriptionOversizedException.class, () -> {
            categoryUseCase.saveCategory(category);
        });
    }

    @Test
    void whenCategoriesListIsProvided_Expects_getAllCategoriesReturnsExpectedList() {
        // Given
        Pagination pagination = new Pagination(1, 10, "name", true);
        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");
        List<Category> expectedCategories = Arrays.asList(category1, category2);
        // When
        when(categoryPersistencePort.getAllCategories(pagination)).thenReturn(expectedCategories);

        List<Category> actualCategories = categoryUseCase.getAllCategories(pagination);

        // Assert
        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    void When_TotalElementsNumberIsProvided_Expect_getTotalElementsReturnsExpectedNumber() {
        // Given: Expected Value
        long expectedTotalElements = 100;
        // Mock behavior
        when(categoryPersistencePort.getTotalElements()).thenReturn(expectedTotalElements);
        // Then
        long actualTotalElements = categoryUseCase.getTotalElements();
        // Assert
        assertEquals(expectedTotalElements, actualTotalElements);
    }
}
