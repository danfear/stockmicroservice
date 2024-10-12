package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.CategorySimpleResponse;
import com.emazon.stockmicroservice.application.dto.ItemResponse;
import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemResponseMapper {

    @Mapping(source = "brand.id", target = "brandId")
    ItemResponse toItemResponse(Item item);

    default List<CategorySimpleResponse> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> {
                    CategorySimpleResponse response = new CategorySimpleResponse();
                    response.setId(category.getId());
                    response.setName(category.getName());
                    return response;
                })
                .toList();
    }
}
