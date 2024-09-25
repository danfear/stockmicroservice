package com.emazon.stockmicroservice.domain.exception.itemexceptions;

import com.emazon.stockmicroservice.domain.util.ItemConstants;

public class ItemCategoryUniqueException extends RuntimeException {
    public ItemCategoryUniqueException() {
        super(ItemConstants.CATEGORY_UNIQUE_MESSAGE);
    }
}
