package com.emazon.stockmicroservice.domain.api;

import com.emazon.stockmicroservice.domain.model.Brand;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
}
