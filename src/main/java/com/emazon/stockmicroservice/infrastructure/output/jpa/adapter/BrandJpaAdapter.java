package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockmicroservice.infrastructure.exception.BrandAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }
}
