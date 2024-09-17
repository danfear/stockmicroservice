package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.infrastructure.exception.BrandAlreadyExistsException;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.IBrandRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
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
    void When_BrandDoesNotExist_Expect_BrandEntityToBeSaved() {
        // Given
        Brand brand = new Brand(50L, "Oster", "Electronic Devices");
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Oster");
        when(brandRepository.findByName("Oster")).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);
        // When
        brandJpaAdapter.saveBrand(brand);
        // Then
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void When_BrandExists_Expect_ThrowsBrandAlreadyExistsException() {
        // Given
        Brand brand = new Brand(4L, "Oster", "Electronic Devices");

        when(brandRepository.findByName("Oster")).thenReturn(Optional.of(new BrandEntity()));

        // Assert
        assertThrows(BrandAlreadyExistsException.class, () -> {
            brandJpaAdapter.saveBrand(brand);
        });
    }

    @Test
    void when_ExpectedCountIsProvided_Expect_GetTotalElementsAssertsExpected() {
        long expectedTotal = 5L;

        when(brandRepository.count()).thenReturn(expectedTotal);

        long result = brandJpaAdapter.getTotalElements();

        assertEquals(expectedTotal, result);
    }
}