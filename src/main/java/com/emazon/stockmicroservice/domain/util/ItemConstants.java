package com.emazon.stockmicroservice.domain.util;

public class ItemConstants {

    private ItemConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_MINIMUM_MESSAGE = "An item must have at least one category associated";
    public static final String CATEGORY_MAXIMUM_MESSAGE = "An item cannot have more than three categories associated";
    public static final String CATEGORY_UNIQUE_MESSAGE = "Categories must be unique";
    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Some categories do not exist in the database";

    public static final int CATEGORY_MINIMUM = 1;
    public static final int CATEGORY_MAXIMUM = 3;
}
