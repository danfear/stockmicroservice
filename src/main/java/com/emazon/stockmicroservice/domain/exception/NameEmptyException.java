package com.emazon.stockmicroservice.domain.exception;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

public class NameEmptyException extends RuntimeException {

    public NameEmptyException() {
        super(DomainConstants.NAME_NULL_MESSAGE);
    }
}

