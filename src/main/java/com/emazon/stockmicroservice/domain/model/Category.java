package com.emazon.stockmicroservice.domain.model;

import com.emazon.stockmicroservice.domain.exception.EmptyFieldException;
import com.emazon.stockmicroservice.domain.exception.OversizedFieldException;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Category {

    public final Long id;
    public final String name;
    public final String description;


    public Category(Long id, String name, String description) {


        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if (name.length() > DomainConstants.MAXSIZENAME) {
            throw new OversizedFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description.length() > DomainConstants.MAXSIZEDESCRIPTION) {
            throw new OversizedFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }

        this.id = id;
        this.name = name; requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = description; requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

}
