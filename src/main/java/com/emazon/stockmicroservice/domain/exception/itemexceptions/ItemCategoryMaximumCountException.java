package com.emazon.stockmicroservice.domain.exception.itemexceptions;

import com.emazon.stockmicroservice.domain.util.ItemConstants;

public class ItemCategoryMaximumCountException extends RuntimeException {
    public ItemCategoryMaximumCountException() {
        super(ItemConstants.CATEGORY_MAXIMUM_MESSAGE);
    }
}
