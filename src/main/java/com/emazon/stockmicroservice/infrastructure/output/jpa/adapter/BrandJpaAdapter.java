package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.exception.NoDataFoundException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public List<Brand> getAllBrands(Pagination pagination) {
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                pagination.isAscending() ? Sort.by(pagination.getSortBy()).ascending()
                        : Sort.by(pagination.getSortBy()).descending()
        );
        Page<BrandEntity> brandPage = brandRepository.findAll(pageable);
        if (brandPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return brandEntityMapper.toBrandList(brandPage.getContent());
    }

    @Override
    public long getTotalElements() {
        return brandRepository.count();
    }
}
