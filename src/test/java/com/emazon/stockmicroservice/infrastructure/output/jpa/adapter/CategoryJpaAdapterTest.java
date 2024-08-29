package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ICategoryEntityMapper categoryEntityMapper;
    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @Test
    void When_CategoryDoesNotExist_Expect_CategoryEntityToBeSaved() {
        // Given
        Category category = new Category(50L, "Electronicos", "Productos electronicos");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Electronicos");
        when(categoryRepository.findByName("Electronicos")).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);
        // When
        categoryJpaAdapter.saveCategory(category);
        // Then
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void When_CategoryExists_Expect_ThrowsCategoryAlreadyExistsException() {
        // Given
        Category category = new Category(4L, "herramientas", "a");

        when(categoryRepository.findByName("herramientas")).thenReturn(Optional.of(new CategoryEntity()));

        // Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryJpaAdapter.saveCategory(category);
        });
    }

}
