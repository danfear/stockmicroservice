package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.api.ItemServicePort;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.*;
import com.emazon.stockmicroservice.domain.exception.itemexceptions.ItemAlreadyExistsException;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.domain.util.ItemConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemUseCase implements ItemServicePort {
    private final ItemPersistencePort itemPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ItemUseCase(ItemPersistencePort itemPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveItem(Item item) {
        validateItem(item);
        validateItemCategories(item);
        itemPersistencePort.saveItem(item);
    }

    private void validateItem(Item item){
        if (itemPersistencePort.existsByName(item.getName())) {
            throw new ItemAlreadyExistsException();
        }
        List<Category> categories = item.getCategories();
        if (categories.size() < ItemConstants.CATEGORY_MINIMUM) {
            throw new ItemCategoryMinimumCountException();
        }
        if (categories.size() > ItemConstants.CATEGORY_MAXIMUM) {
            throw new ItemCategoryMaximumCountException();
        }
        Set<Long> categoryIDs = new HashSet<>();
        for (Category category : categories) {
            if (!categoryIDs.add(category.getId())) {
                throw new ItemCategoryUniqueException();
            }
        }
    }
    private void validateItemCategories(Item item) {
        List<Long> categoryIds = item.getCategories().stream()
                .map(Category::getId)
                .toList();
        List<Long> existingCategoryIds = categoryPersistencePort.findExistingCategoriesByIds(categoryIds);
        if (existingCategoryIds.size() != categoryIds.size()) {
            throw new ItemCategoryNotFoundException();
        }
    }

}
