package com.emazon.stockmicroservice.domain.exception;
import com.emazon.stockmicroservice.domain.util.DomainConstants;

public class NameOversizedException extends RuntimeException {
    public NameOversizedException() {
        super(DomainConstants.NAME_OVERSIZED_MESSAGE);
    }
}
