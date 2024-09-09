package com.emazon.stockmicroservice.domain.usecase.categoryusecase;

import com.emazon.stockmicroservice.domain.exception.DescriptionOversizedException;
import com.emazon.stockmicroservice.domain.exception.NameOversizedException;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.exception.NameEmptyException;
import com.emazon.stockmicroservice.domain.exception.DescriptionEmptyException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveCategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase saveCategoryUseCase;

    @Test
    void When_CategoryInformationIsCorrect_Expect_CategoryToBeSavedSuccessfully() {
        //Given
        Category category = new Category(50L,"Electronicos", "Productos electronicos");
        //When
        this.saveCategoryUseCase.saveCategory(category);
        //Then
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(this.categoryPersistencePort).saveCategory(categoryArgumentCaptor.capture());
        assertEquals(50L,categoryArgumentCaptor.getValue().getId());
        assertEquals("Electronicos",categoryArgumentCaptor.getValue().getName());
        assertEquals("Productos electronicos",categoryArgumentCaptor.getValue().getDescription());
    }

    @Test
    void When_CategoryNameIsEmpty_Expect_NameEmptyException() {
        //Given
        Category category = new Category(50L,"", "Productos electronicos");
        //Then
        assertThrows(NameEmptyException.class, () -> {
            saveCategoryUseCase.saveCategory(category);
        });
    }

    @Test
    void When_CategoryDescriptionIsEmpty_Expect_DescriptionEmptyException() {
        //Given
        Category category = new Category(50L,"Electronicos", "");
        //Then
        assertThrows(DescriptionEmptyException.class, () -> {
            saveCategoryUseCase.saveCategory(category);
        });
    }

    @Test
    void When_CategoryNameIsOverMaxLength_Expect_NameOversizedException() {
        //Given
        Category category = new Category(50L,"Electronicossssssssssssssssssssssssssssssssssssssss",
                "Productos electronicos");
        //Then
        assertThrows(NameOversizedException.class, () -> {
            saveCategoryUseCase.saveCategory(category);
        });
    }

    @Test
    void When_CategoryDescriptionIsOverMaxLength_Expect_DescriptionOversizedException() {
        //Given
        Category category = new Category(50L,"Electronicos",
                "Productos electronicossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        //Then
        assertThrows(DescriptionOversizedException.class, () -> {
            saveCategoryUseCase.saveCategory(category);
        });
    }


}
