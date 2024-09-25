package com.emazon.stockmicroservice.infrastructure.output.jpa.mapper;

import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ICategoryEntityMapper.class, IBrandEntityMapper.class})
public interface ItemEntityMapper {
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    Item toItem(ItemEntity itemEntity);

    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ItemEntity toEntity(Item item);
}
