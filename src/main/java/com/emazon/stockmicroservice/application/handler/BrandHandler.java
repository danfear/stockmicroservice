package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.application.mapper.IBrandRequestMapper;
import com.emazon.stockmicroservice.application.mapper.IBrandResponseMapper;
import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.util.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandServicePort brandServicePort;
    private final IBrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrandInStock(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public PaginatedResponse<BrandResponse> getAllBrandsFromStock(Pagination pagination) {
        List<Brand> brands = brandServicePort.getAllBrands(pagination);
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(brands);

        long totalElements = brandServicePort.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalElements / pagination.getSize());

        return new PaginatedResponse<>(
                brandResponses,
                pagination.getPage(),
                pagination.getSize(),
                pagination.getSortBy(),
                pagination.isAscending(),
                totalElements,
                totalPages
        );
    }
}
