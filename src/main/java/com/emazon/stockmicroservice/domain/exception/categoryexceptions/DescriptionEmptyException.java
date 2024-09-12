package com.emazon.stockmicroservice.domain.exception.categoryexceptions;
import com.emazon.stockmicroservice.domain.util.CategoryConstants;

public class DescriptionEmptyException extends RuntimeException {
    public DescriptionEmptyException() {
        super(CategoryConstants.DESCRIPTION_NULL_MESSAGE);
    }
}
