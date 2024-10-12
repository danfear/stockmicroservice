package com.emazon.stockmicroservice.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySummary {
    private Long id;
    private String name;
    public CategorySummary(Long id, String name) {
    }
}
