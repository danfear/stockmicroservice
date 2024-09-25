package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.exception.categoryexceptions.*;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.util.CategoryConstants;
import com.emazon.stockmicroservice.domain.util.Pagination;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        validateCategory(category);
        categoryPersistencePort.saveCategory(category);
    }
    private void validateCategory(Category category){
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new NameEmptyException();
        }
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException();
        }
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new DescriptionEmptyException();
        }
        if (category.getName().length() > CategoryConstants.MAX_NAME_LENGTH) {
            throw new NameOversizedException();
        }
        if (category.getDescription().length() > CategoryConstants.MAX_DESCRIPTION_LENGTH) {
            throw new DescriptionOversizedException();
        }
    }

    @Override
    public List<Category> getAllCategories(Pagination pagination) {
        return categoryPersistencePort.getAllCategories(pagination);
    }
    @Override
    public long getTotalElements() {
        return categoryPersistencePort.getTotalElements();
    }
}
