package com.emazon.stockmicroservice.domain.exception.brandexceptions;

import com.emazon.stockmicroservice.domain.util.BrandConstants;

public class NameOversizedException extends RuntimeException {
    public NameOversizedException() {
        super(BrandConstants.NAME_OVERSIZED_MESSAGE);
    }
}
