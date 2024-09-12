package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Brand;

public interface IBrandPersistencePort{
    void saveBrand(Brand brand);
}
