package com.emazon.stockmicroservice.domain.exception;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

public class DescriptionOversizedException extends RuntimeException {
    public DescriptionOversizedException() {
        super(DomainConstants.DESCRIPTION_OVERSIZED_MESSAGE);
    }
}
