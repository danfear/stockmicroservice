package com.emazon.stockmicroservice.infrastructure.output.jpa.repository;

import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String itemName);
}
