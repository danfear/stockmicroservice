package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.util.Pagination;

import java.util.List;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    List<Brand> getAllBrands(Pagination pagination);
    long getTotalElements();
}
