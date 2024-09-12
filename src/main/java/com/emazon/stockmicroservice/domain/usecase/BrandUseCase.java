package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.DescriptionEmptyException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.DescriptionOversizedException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.NameEmptyException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.NameOversizedException;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;
import com.emazon.stockmicroservice.domain.util.BrandConstants;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().trim().isEmpty()) {
            throw new NameEmptyException();
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
        brandPersistencePort.saveBrand(brand);
    }
}
