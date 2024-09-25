package com.emazon.stockmicroservice.infrastructure.output.jpa.mapper;

import com.emazon.stockmicroservice.domain.model.Category;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.CategoryEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}
