package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse toResponse(Brand brand);

    default List<BrandResponse> toResponseList(List<Brand> categoryList) {
        return categoryList.stream()
                .map(category -> {
                    BrandResponse brandResponse = new BrandResponse();
                    brandResponse.setName(category.getName());
                    brandResponse.setDescription(category.getDescription());
                    return brandResponse;
                }).toList();
    }
}
