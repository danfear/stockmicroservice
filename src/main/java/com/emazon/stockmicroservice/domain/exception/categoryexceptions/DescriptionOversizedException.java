package com.emazon.stockmicroservice.domain.exception.categoryexceptions;
import com.emazon.stockmicroservice.domain.util.CategoryConstants;

public class DescriptionOversizedException extends RuntimeException {
    public DescriptionOversizedException() {
        super(CategoryConstants.DESCRIPTION_OVERSIZED_MESSAGE);
    }
}
