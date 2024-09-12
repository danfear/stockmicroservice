package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.mapper.IBrandRequestMapper;
import com.emazon.stockmicroservice.application.mapper.IBrandResponseMapper;
import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.model.Brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

class BrandHandlerTest {
    @InjectMocks
    private BrandHandler brandHandler;
    @Mock
    private IBrandRequestMapper brandRequestMapper;
    @Mock
    private IBrandServicePort brandServicePort;
    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_BrandResquestIsCorrect_Expect_SaveBrandInStockSuccess() {
        // Given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Oster");
        brandRequest.setDescription("Electronic Devices");

        Brand brand = new Brand(50L, "Oster", "Electronic Devices");

        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);

        // When
        brandHandler.saveBrandInStock(brandRequest);

        // Then
        verify(brandRequestMapper, times(1)).toBrand(brandRequest);
        verify(brandServicePort, times(1)).saveBrand(brand);
    }

    @Test
    void when_BrandResquestIsCorrect_Expect_SaveBrandInStockFailure() {
        // Given
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Oster");
        brandRequest.setDescription("Devices");

        Brand brand = new Brand(50L, "Oster", "Electronic Devices");

        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        doThrow(new RuntimeException("Save failed")).when(brandServicePort).saveBrand(brand);

        // When & Then
        try {
            brandHandler.saveBrandInStock(brandRequest);
            brandHandler.saveBrandInStock(brandRequest);
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(brandRequestMapper, times(1)).toBrand(brandRequest);
        verify(brandServicePort, times(1)).saveBrand(brand);
    }

}