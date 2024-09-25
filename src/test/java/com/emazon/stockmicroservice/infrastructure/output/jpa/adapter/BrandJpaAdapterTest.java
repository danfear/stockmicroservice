package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.IBrandRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandJpaAdapterTest {
    @Mock
    private IBrandRepository brandRepository;
    @Mock
    private IBrandEntityMapper brandEntityMapper;
    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @Test
    void when_DataBaseHasBrands_Expect_GetAllBrandsSuccessfully() {
        Pagination pagination = new Pagination(0, 10, "name", true);
        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(pagination.getSortBy()).ascending()
        );

        List<ItemEntity> emptyItemList = new ArrayList<>(); // Lista vacía de items

        BrandEntity brandEntity1 = new BrandEntity(1L,  "Electrolux", "Productos electronicos", emptyItemList);
        BrandEntity brandEntity2 = new BrandEntity(2L, "Oster", "Dispositivos de cocina", emptyItemList);
        List<BrandEntity> brandEntities = List.of(brandEntity1, brandEntity2);

        Brand brand1 = new Brand(1L, "Electrolux", "Productos electronicos");
        Brand brand2 = new Brand(2L, "Oster", "Dispositivos de cocina");
        List<Brand> categories = List.of(brand1, brand2);

        Page<BrandEntity> brandPage = new PageImpl<>(brandEntities, pageable, brandEntities.size());

        when(brandRepository.findAll(pageable)).thenReturn(brandPage);
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(categories);

        List<Brand> result = brandJpaAdapter.getAllBrands(pagination);

        assertEquals(2, result.size());
        assertEquals("Electrolux", result.get(0).getName());
        assertEquals("Oster", result.get(1).getName());
    }

    @Test
    void when_ExpectedCountIsProvided_Expect_GetTotalElementsAssertsExpected() {
        long expectedTotal = 5L;

        when(brandRepository.count()).thenReturn(expectedTotal);

        long result = brandJpaAdapter.getTotalElements();

        assertEquals(expectedTotal, result);
    }
}