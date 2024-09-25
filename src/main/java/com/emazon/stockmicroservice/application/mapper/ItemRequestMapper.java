package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.ItemRequest;
import com.emazon.stockmicroservice.domain.model.Brand;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemRequestMapper {

    @Mapping(target = "brand", expression = "java(mapBrand(itemRequest.getBrandId()))")
    @Mapping(target = "categories", expression = "java(mapCategories(itemRequest.getCategoryIds()))")
    @Mapping(target = "id", ignore = true)
    Item toItem(ItemRequest itemRequest);

    // brand mapping
    default Brand mapBrand(Long brandId) {
        return new Brand(brandId, null, null); // Create brand object only with ID
    }

    // Category mapping
    default List<Category> mapCategories(List<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> new Category(id, null, null)) // Create category objects only with IDs
                .toList();
    }
}
