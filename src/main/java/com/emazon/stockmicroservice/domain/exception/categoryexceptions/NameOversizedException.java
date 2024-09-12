package com.emazon.stockmicroservice.domain.exception.categoryexceptions;
import com.emazon.stockmicroservice.domain.util.CategoryConstants;

public class NameOversizedException extends RuntimeException {
    public NameOversizedException() {
        super(CategoryConstants.NAME_OVERSIZED_MESSAGE);
    }
}
