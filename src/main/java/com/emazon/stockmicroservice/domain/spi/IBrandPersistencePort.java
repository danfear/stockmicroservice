package com.emazon.stockmicroservice.domain.spi;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.util.Pagination;

import java.util.List;

public interface IBrandPersistencePort{
    void saveBrand(Brand brand);
    boolean existsByName(String name);
    List<Brand> getAllBrands(Pagination pagination);
    long getTotalElements();
}
