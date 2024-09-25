package com.emazon.stockmicroservice.domain.exception.itemexceptions;

import com.emazon.stockmicroservice.domain.util.ItemConstants;

public class ItemCategoryNotFoundException extends RuntimeException {
    public ItemCategoryNotFoundException() {
        super(ItemConstants.CATEGORY_NOT_FOUND_MESSAGE);
    }
}
