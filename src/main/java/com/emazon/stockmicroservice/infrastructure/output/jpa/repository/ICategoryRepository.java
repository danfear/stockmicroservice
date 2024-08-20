package com.emazon.stockmicroservice.infrastructure.output.jpa.repository;

import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String categoryName);
}
