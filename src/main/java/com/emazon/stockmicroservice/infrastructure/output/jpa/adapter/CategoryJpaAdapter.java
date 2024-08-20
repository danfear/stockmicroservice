package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity((category)));
    }
}
