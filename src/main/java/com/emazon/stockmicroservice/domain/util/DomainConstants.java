package com.emazon.stockmicroservice.domain.util;

public final class DomainConstants {

    public DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_NAME_OVERSIZED_MESSAGE = "Field 'name' cannot be longer than 50 characters";
    public static final String FIELD_DESCRIPTION_OVERSIZED_MESSAGE = "Field 'description' cannot be longer than 90 characters";

    public static final Long MAXSIZENAME = 50L;
    public static final Long MAXSIZEDESCRIPTION = 90L;



}
