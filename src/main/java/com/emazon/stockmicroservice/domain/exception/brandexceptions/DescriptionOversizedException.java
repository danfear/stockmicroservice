package com.emazon.stockmicroservice.domain.exception.brandexceptions;

import com.emazon.stockmicroservice.domain.util.BrandConstants;

public class DescriptionOversizedException extends RuntimeException {
    public DescriptionOversizedException() {
        super(BrandConstants.DESCRIPTION_OVERSIZED_MESSAGE);
    }
}
