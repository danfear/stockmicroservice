package com.emazon.stockmicroservice.domain.exception.brandexceptions;

import com.emazon.stockmicroservice.domain.util.BrandConstants;

public class NameEmptyException extends RuntimeException {

    public NameEmptyException() {
        super(BrandConstants.NAME_NULL_MESSAGE);
    }
}
