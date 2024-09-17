package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;

public interface IBrandHandler {
    void saveBrandInStock(BrandRequest brandRequest);
    PaginatedResponse<BrandResponse> getAllBrandsFromStock(Pagination pagination);

}
