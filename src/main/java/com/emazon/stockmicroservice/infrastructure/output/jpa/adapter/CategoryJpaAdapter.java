package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.exception.NoDataFoundException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }


    @Override
    public List<Category> getAllCategories(Pagination pagination) {
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                pagination.isAscending() ? Sort.by(pagination.getSortBy()).ascending()
                        : Sort.by(pagination.getSortBy()).descending()
        );

        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        if (categoryPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryPage.getContent());
    }

    @Override
    public long getTotalElements() {
        return categoryRepository.count();
    }
}
