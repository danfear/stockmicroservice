package com.emazon.stockmicroservice.domain.usecase;

import com.emazon.stockmicroservice.domain.exception.brandexceptions.DescriptionEmptyException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.DescriptionOversizedException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.NameEmptyException;
import com.emazon.stockmicroservice.domain.exception.brandexceptions.NameOversizedException;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.spi.IBrandPersistencePort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    void When_BrandInformationIsCorrect_Expect_BrandToBeSavedSuccessfully() {
        //Given
        Brand brand = new Brand(50L,"Oster", "Productos electronicos");
        //When
        this.brandUseCase.saveBrand(brand);
        //Then
        ArgumentCaptor<Brand> brandArgumentCaptor = ArgumentCaptor.forClass(Brand.class);
        verify(this.brandPersistencePort).saveBrand(brandArgumentCaptor.capture());
        assertEquals(50L,brandArgumentCaptor.getValue().getId());
        assertEquals("Oster",brandArgumentCaptor.getValue().getName());
        assertEquals("Productos electronicos",brandArgumentCaptor.getValue().getDescription());
    }

    @Test
    void When_BrandNameIsEmpty_Expect_NameEmptyException() {
        //Given
        Brand brand = new Brand(50L,"", "Productos electronicos");
        //Then
        assertThrows(NameEmptyException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    @Test
    void When_BrandDescriptionIsEmpty_Expect_DescriptionEmptyException() {
        //Given
        Brand brand = new Brand(50L,"Electronicos", "");
        //Then
        assertThrows(DescriptionEmptyException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    @Test
    void When_BrandNameIsOverMaxLength_Expect_NameOversizedException() {
        //Given
        Brand brand = new Brand(50L,"Osterrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                "Productos electronicos");
        //Then
        assertThrows(NameOversizedException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    @Test
    void When_BrandDescriptionIsOverMaxLength_Expect_DescriptionOversizedException() {
        //Given
        Brand brand = new Brand(50L,"Oster",
                "Productos electronicossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        //Then
        assertThrows(DescriptionOversizedException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

}