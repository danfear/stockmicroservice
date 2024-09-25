package com.emazon.stockmicroservice.infrastructure.configuration;

import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.api.ICategoryServicePort;
import com.emazon.stockmicroservice.domain.api.ItemServicePort;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockmicroservice.domain.spi.ICategoryPersistencePort;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.domain.usecase.BrandUseCase;
import com.emazon.stockmicroservice.domain.usecase.CategoryUseCase;
import com.emazon.stockmicroservice.domain.usecase.ItemUseCase;
import com.emazon.stockmicroservice.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.stockmicroservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stockmicroservice.infrastructure.output.jpa.adapter.ItemJpaAdapter;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ItemEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.IBrandRepository;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final ItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }
    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public ItemPersistencePort itemPersistencePort() {
        return new ItemJpaAdapter(itemRepository, itemEntityMapper);
    }
    @Bean
    public ItemServicePort itemServicePort() {
        return new ItemUseCase(itemPersistencePort(), categoryPersistencePort());
    }

}
