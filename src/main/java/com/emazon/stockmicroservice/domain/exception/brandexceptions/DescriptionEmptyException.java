package com.emazon.stockmicroservice.domain.exception.brandexceptions;

import com.emazon.stockmicroservice.domain.util.BrandConstants;

public class DescriptionEmptyException extends RuntimeException {
    public DescriptionEmptyException() {
        super(BrandConstants.DESCRIPTION_NULL_MESSAGE);
    }
}
