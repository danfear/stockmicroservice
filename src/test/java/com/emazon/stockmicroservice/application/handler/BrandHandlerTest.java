package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.application.dto.PaginatedResponse;
import com.emazon.stockmicroservice.application.mapper.IBrandRequestMapper;
import com.emazon.stockmicroservice.application.mapper.IBrandResponseMapper;
import com.emazon.stockmicroservice.domain.api.IBrandServicePort;
import com.emazon.stockmicroservice.domain.model.Brand;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.util.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void when_ParametersAreCorrect_Expect_GetAllBrandsFromStockSuccessfully() {
        Pagination pagination = new Pagination(0, 10, "name", true);
        Brand brand1 = new Brand(1L, "Oster", "Electronic Devices");
        Brand brand2 = new Brand(2L, "Mabe", "Kitchen electronics");

        List<Brand> brands = Arrays.asList(brand1, brand2);

        BrandResponse response1 = new BrandResponse();
        response1.setName("Oster");
        response1.setDescription("Electronic Devices");

        BrandResponse response2 = new BrandResponse();
        response2.setName("Mabe");
        response2.setDescription("Kitchen electronics");

        List<BrandResponse> responses = Arrays.asList(response1, response2);

        when(brandServicePort.getAllBrands(pagination)).thenReturn(brands);
        when(brandResponseMapper.toResponseList(brands)).thenReturn(responses);
        when(brandServicePort.getTotalElements()).thenReturn(2L);

        PaginatedResponse<BrandResponse> result = brandHandler.getAllBrandsFromStock(pagination);

        assertEquals(2, result.getData().size());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals("name", result.getSortBy());
        assertTrue(result.isAscending());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }

}