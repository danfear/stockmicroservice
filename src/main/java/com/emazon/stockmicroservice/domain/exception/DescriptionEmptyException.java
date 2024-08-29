package com.emazon.stockmicroservice.domain.exception;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

public class DescriptionEmptyException extends RuntimeException {
    public DescriptionEmptyException() {
        super(DomainConstants.DESCRIPTION_NULL_MESSAGE);
    }
}
