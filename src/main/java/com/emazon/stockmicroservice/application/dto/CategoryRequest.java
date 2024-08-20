package com.emazon.stockmicroservice.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private String name;
    private String description;
}
