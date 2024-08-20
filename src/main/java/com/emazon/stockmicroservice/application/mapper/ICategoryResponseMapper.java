package com.emazon.stockmicroservice.application.mapper;

import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

        CategoryResponse toResponse(Category category);


}
