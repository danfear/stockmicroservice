package com.emazon.stockmicroservice.infrastructure.output.jpa.adapter;

import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;
import com.emazon.stockmicroservice.infrastructure.output.jpa.entity.ItemEntity;
import com.emazon.stockmicroservice.infrastructure.output.jpa.mapper.ItemEntityMapper;
import com.emazon.stockmicroservice.infrastructure.output.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

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

    @Override
    public PaginatedResponse<Item> getAllItems(Pagination paginationRequest) {
        Page<ItemEntity> itemPage = itemRepository.findAll(PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()));

        List<Item> items = itemPage.stream()
                .map(itemEntityMapper::toItem)
                .toList();

        return new PaginatedResponse<>(items, itemPage.getNumber(), itemPage.getSize(),
                "name", true, itemPage.getTotalElements(), itemPage.getTotalPages());
    }

    @Override
    public long getTotalElements() {
        return itemRepository.count();
    }
}
