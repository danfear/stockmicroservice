package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.mapper.IBrandRequestMapper;
import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.model.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandServicePort brandServicePort;
    @Override
    public void saveBrandInStock(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }
}
