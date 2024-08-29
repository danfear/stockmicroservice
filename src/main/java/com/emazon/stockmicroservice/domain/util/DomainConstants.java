package com.emazon.stockmicroservice.domain.util;

public final class DomainConstants {

    public DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final String NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String NAME_OVERSIZED_MESSAGE = "Field 'name' cannot be longer than 50 characters";
    public static final String DESCRIPTION_OVERSIZED_MESSAGE = "Field 'description' cannot be longer than 90 characters";

    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 90;


}
