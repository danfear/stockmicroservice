package com.emazon.stockmicroservice.application.handler;

import com.emazon.stockmicroservice.application.dto.ItemRequest;
import com.emazon.stockmicroservice.application.mapper.ItemRequestMapper;
import com.emazon.stockmicroservice.domain.api.ItemServicePort;
import com.emazon.stockmicroservice.domain.model.Item;
import com.emazon.stockmicroservice.domain.spi.ItemPersistencePort;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.domain.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemHandler implements IItemHandler{

    private final ItemRequestMapper itemRequestMapper;
    private final ItemServicePort itemServicePort;
    private final ItemPersistencePort itemPersistencePort;
    @Override
    public void saveItemInStock(ItemRequest itemRequest) {
        Item item = itemRequestMapper.toItem(itemRequest);
        itemServicePort.saveItem(item);
    }

    @Override
    public PaginatedResponse<Item> getAllItems(Pagination paginationRequest) {
        return itemPersistencePort.getAllItems(paginationRequest);
    }
}
