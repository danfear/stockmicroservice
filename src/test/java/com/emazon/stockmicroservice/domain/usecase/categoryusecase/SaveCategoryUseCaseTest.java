package com.emazon.stockmicroservice.domain.usecase.categoryusecase;


import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveCategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private SaveCategoryUseCase saveCategoryUseCase;

    @Test
    void testSaveCategory() {
        //Given
        Category category = new Category(50L,"Electronicos", "Productos electronicos");
        //When
        this.saveCategoryUseCase.saveCategory(category);
        //Then
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(this.categoryPersistencePort).saveCategory(categoryArgumentCaptor.capture());
        assertEquals(50L,categoryArgumentCaptor.getValue().getId());
        assertEquals("Electronicos",categoryArgumentCaptor.getValue().getName());
    }

}
