package com.emazon.stockmicroservice.application.handler.categoryhandler;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;

public interface ISaveCategoryHandler {

    void saveCategoryInStock(CategoryRequest categoryRequest);
}
