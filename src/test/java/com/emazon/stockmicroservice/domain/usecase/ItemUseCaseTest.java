package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.exception.itemexceptions.*;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ItemUseCaseTest {
    private ItemPersistencePort itemPersistencePort;
    private ICategoryPersistencePort categoryPersistencePort;
    private ItemUseCase itemUseCase;

    @BeforeEach
    void setUp() {
        itemPersistencePort = mock(ItemPersistencePort.class);
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        itemUseCase = new ItemUseCase(itemPersistencePort, categoryPersistencePort);
    }

    @Test
    void when_ItemAlreadyExists_Expect_ThrowsException() {
        //Given: item with a minimum number of categories
        List<Category> categories = List.of(
                new Category(1L, "Category1", "Description1"),
                new Category(2L, "Category2", "Description2")
        );
        Item item = new Item(1L, "Item", "Description", 10, 99.99, new Brand(1L, "Brand", "Description"), categories);
        //When: simulates item already exits
        when(itemPersistencePort.existsByName(item.getName())).thenReturn(true);
        //Then: Throws exception
        assertThrows(ItemAlreadyExistsException.class, () -> itemUseCase.saveItem(item));
        verify(itemPersistencePort).existsByName(item.getName());
        verify(itemPersistencePort, never()).saveItem(any());
    }

    @Test
    void when_CategoryCountIsLessThanMinimum_Expect_ThrowsException() {
        //Given: item without categories
        List<Category> categories = List.of();
        Item item = new Item(1L, "NewItem", "Item description", 10, 99.99, new Brand(1L, "Brand", "Brand Description"), categories);

        // When:Simulates categories already exists
        when(categoryPersistencePort.findExistingCategoriesByIds(anyList())).thenReturn(List.of());

        // Then: Throws exception
        assertThrows(ItemCategoryMinimumCountException.class, () -> itemUseCase.saveItem(item));

        // Then: Verify item wont be saved
        verify(itemPersistencePort, never()).saveItem(any());
    }

    @Test
    void when_CategoryCountIsGreaterThanMaximum_Expect_ThrowsException() {
        // Given
        Item item = mock(Item.class);
        List<Category> categories = Arrays.asList(
                mock(Category.class), mock(Category.class), mock(Category.class), mock(Category.class)
        );
        when(item.getCategories()).thenReturn(categories);
        // When & Then
        assertThrows(ItemCategoryMaximumCountException.class, () -> itemUseCase.saveItem(item));
    }

    @Test
    void when_CategoriesAreNotUnique_Expect_ThrowsException() {
        // Given
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        when(category1.getId()).thenReturn(1L);
        when(category2.getId()).thenReturn(1L); // Mismo ID

        Item item = mock(Item.class);
        when(item.getCategories()).thenReturn(Arrays.asList(category1, category2));
        // When & Then
        assertThrows(ItemCategoryUniqueException.class, () -> itemUseCase.saveItem(item));
    }

    @Test
    void when_CategoryDoesNotExistInDatabase_Expect_ThrowsException() {
        // Given
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(1L);

        Item item = mock(Item.class);
        when(item.getCategories()).thenReturn(Collections.singletonList(category));

        when(categoryPersistencePort.findExistingCategoriesByIds(anyList()))
                .thenReturn(Collections.emptyList()); // Not found in DB
        // When & Then
        assertThrows(ItemCategoryNotFoundException.class, () -> itemUseCase.saveItem(item));
    }

    @Test
    void when_ValidationsArePassed_Expect_ItemToBeSaved() {
        // Given
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        when(category1.getId()).thenReturn(1L);
        when(category2.getId()).thenReturn(2L);

        Item item = mock(Item.class);
        when(item.getCategories()).thenReturn(Arrays.asList(category1, category2));
        when(categoryPersistencePort.findExistingCategoriesByIds(anyList()))
                .thenReturn(Arrays.asList(1L, 2L));
        // When
        itemUseCase.saveItem(item);
        // Then
        verify(itemPersistencePort).saveItem(item);
    }

    @Test
    void shouldReturnPaginatedResponseWhenGettingAllItems() {
        Pagination paginationRequest = new Pagination(0, 10, "name", true);
        PaginatedResponse<Item> expectedResponse = new PaginatedResponse<>(
                List.of(createValidItem()),
                0,
                10,
                "name",
                true,
                1,
                1
        );

        when(itemPersistencePort.getAllItems(paginationRequest)).thenReturn(expectedResponse);

        PaginatedResponse<Item> response = itemUseCase.getAllItems(paginationRequest);

        assertEquals(expectedResponse, response);
    }

    //aux
    private Item createValidItem() {
        return new Item(1L, "ItemName", "Description", 10, 100.0, createValidBrand(), createValidCategories());
    }

    private Brand createValidBrand() {
        return new Brand(1L, "BrandName", "BrandDescription");
    }

    private List<Category> createValidCategories() {
        return List.of(
                new Category(1L, "Electronics", "CategoryDescription"),
                new Category(2L, "Books", "CategoryDescription")
        );
    }
}
