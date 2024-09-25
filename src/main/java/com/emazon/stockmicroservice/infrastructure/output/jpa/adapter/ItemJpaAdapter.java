package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ItemEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemJpaAdapter implements ItemPersistencePort {

    private final ItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;
    @Override
    public void saveItem(Item item) {
        ItemEntity itemEntity = itemEntityMapper.toEntity(item);
        itemRepository.save(itemEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return itemRepository.findByName(name).isPresent();
    }
}
