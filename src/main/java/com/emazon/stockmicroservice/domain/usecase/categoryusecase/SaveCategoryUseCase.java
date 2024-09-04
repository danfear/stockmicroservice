package com.emazon.stockmicroservice.domain.usecase.categoryusecase;

import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.exception.DescriptionEmptyException;
import com.emazon.stockmicroservice.domain.exception.DescriptionOversizedException;
import com.emazon.stockmicroservice.domain.exception.NameEmptyException;
import com.emazon.stockmicroservice.domain.exception.NameOversizedException;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

import java.util.List;

public class SaveCategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public SaveCategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new NameEmptyException();
        }
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new DescriptionEmptyException();
        }
        if (category.getName().length() > DomainConstants.MAX_NAME_LENGTH) {
            throw new NameOversizedException();
        }
        if (category.getDescription().length() > DomainConstants.MAX_DESCRIPTION_LENGTH) {
            throw new DescriptionOversizedException();
        }
        
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories(){
        return categoryPersistencePort.getAllCategories();
    }
}
