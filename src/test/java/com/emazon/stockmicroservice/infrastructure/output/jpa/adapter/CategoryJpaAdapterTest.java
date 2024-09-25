package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.exception.NoDataFoundException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void when_DataBaseHasCategories_Expect_GetAllCategoriesSuccessfully() {
        Pagination pagination = new Pagination(0, 10, "name", true);
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(pagination.getSortBy()).ascending()
        );

        List<ItemEntity> emptyItemList = new ArrayList<>(); // Lista vacía de items

        CategoryEntity categoryEntity1 = new CategoryEntity(1L, "Electronicos", "Productos electronicos", emptyItemList);
        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Libros", "VLibros variados", emptyItemList);
        List<CategoryEntity> categoryEntities = List.of(categoryEntity1, categoryEntity2);

        Category category1 = new Category(1L, "Electronicos", "Productos electronicos");
        Category category2 = new Category(2L, "Libros", "Libros variados");
        List<Category> categories = List.of(category1, category2);

        Page<CategoryEntity> categoryPage = new PageImpl<>(categoryEntities, pageable, categoryEntities.size());

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        List<Category> result = categoryJpaAdapter.getAllCategories(pagination);

        assertEquals(2, result.size());
        assertEquals("Electronicos", result.get(0).getName());
        assertEquals("Libros", result.get(1).getName());
    }

    @Test
    void when_DataBaseHasCategorires_Expect_NoDataFoundException() {
        Pagination pagination = new Pagination(0, 10, "name", true);
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(pagination.getSortBy()).ascending()
        );

        Page<CategoryEntity> categoryPage = Page.empty(pageable);

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        assertThrows(NoDataFoundException.class, () -> categoryJpaAdapter.getAllCategories(pagination));
    }

    @Test
    void when_ExpectedCountIsProvided_Expect_GetTotalElementsAssertsExpected() {
        long expectedTotal = 5L;

        when(categoryRepository.count()).thenReturn(expectedTotal);

        long result = categoryJpaAdapter.getTotalElements();

        assertEquals(expectedTotal, result);
    }

}
