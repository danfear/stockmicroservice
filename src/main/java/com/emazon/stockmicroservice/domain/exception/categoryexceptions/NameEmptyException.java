package com.emazon.stockmicroservice.domain.exception.categoryexceptions;
import com.emazon.stockmicroservice.domain.util.CategoryConstants;

public class NameEmptyException extends RuntimeException {

    public NameEmptyException() {
        super(CategoryConstants.NAME_NULL_MESSAGE);
    }
}

