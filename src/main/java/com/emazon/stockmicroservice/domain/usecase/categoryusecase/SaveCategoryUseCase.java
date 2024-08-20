package com.emazon.stockmicroservice.domain.usecase.categoryusecase;

import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;

public class SaveCategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public SaveCategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }
}
