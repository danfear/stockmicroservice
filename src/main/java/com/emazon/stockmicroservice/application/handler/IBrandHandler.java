package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;

public interface IBrandHandler {
    void saveBrandInStock(BrandRequest brandRequest);

}
