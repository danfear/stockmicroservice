package com.emazon.stockmicroservice.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {
    private String name;
    private String description;
}
