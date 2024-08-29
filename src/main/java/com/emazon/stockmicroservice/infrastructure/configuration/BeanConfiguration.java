package com.emazon.stockmicroservice.infrastructure.configuration;

import com.emazon.stockmicroservice.domain.api.ISaveCategoryServicePort;
import com.emazon.stockmicroservice.domain.spi.ISaveCategoryPersistencePort;
import com.emazon.stockmicroservice.domain.usecase.categoryusecase.SaveCategoryUseCase;
import com.emazon.stockmicroservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ISaveCategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ISaveCategoryServicePort categoryServicePort() {
        return new SaveCategoryUseCase(categoryPersistencePort());
    }

}
