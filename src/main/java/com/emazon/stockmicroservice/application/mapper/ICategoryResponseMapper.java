package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

        CategoryResponse toResponse(Category category);

        default List<CategoryResponse> toResponseList(List<Category> categoryList) {
                return categoryList.stream()
                        .map(category -> {
                                CategoryResponse categoryResponse = new CategoryResponse();
                                categoryResponse.setName(category.getName());
                                categoryResponse.setDescription(category.getDescription());
                                return categoryResponse;
                        }).toList();
        }


}
