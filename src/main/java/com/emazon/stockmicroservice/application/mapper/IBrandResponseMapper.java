package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.BrandRequest;

public interface IBrandResponseMapper {
    void saveBrandInStock(BrandRequest brandRequest);
}
