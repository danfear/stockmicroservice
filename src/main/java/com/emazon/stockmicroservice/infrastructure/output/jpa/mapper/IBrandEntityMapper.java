package com.emazon.stockmicroservice.infrastructure.output.jpa.mapper;

import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
    List<Brand> toBrandList(List<BrandEntity> brandEntityList);
}
