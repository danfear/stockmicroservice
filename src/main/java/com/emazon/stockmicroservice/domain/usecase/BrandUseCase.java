package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.*;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockmicroservice.domain.util.BrandConstants;
import com.emazon.stockmicroservice.domain.util.Pagination;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        validateBrand(brand);
        brandPersistencePort.saveBrand(brand);
    }
    private void validateBrand(Brand brand){
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new NameEmptyException();
        }
        if (brandPersistencePort.existsByName(brand.getName())) {
            throw new BrandAlreadyExistsException();
        }
        if (brand.getDescription() == null || brand.getDescription().trim().isEmpty()) {
            throw new DescriptionEmptyException();
        }
        if (brand.getName().length() > BrandConstants.MAX_NAME_LENGTH) {
            throw new NameOversizedException();
        }
        if (brand.getDescription().length() > BrandConstants.MAX_DESCRIPTION_LENGTH) {
            throw new DescriptionOversizedException();
        }
    }

    @Override
    public List<Brand> getAllBrands(Pagination pagination) {
        return brandPersistencePort.getAllBrands(pagination);
    }
    @Override
    public long getTotalElements() {
        return brandPersistencePort.getTotalElements();
    }
}
