package com.emazon.stockmicroservice.domain.exception.itemexceptions;

import com.emazon.stockmicroservice.domain.util.ItemConstants;

public class ItemCategoryMinimumCountException extends RuntimeException {
    public ItemCategoryMinimumCountException() {
        super(ItemConstants.CATEGORY_MINIMUM_MESSAGE);
    }
}
