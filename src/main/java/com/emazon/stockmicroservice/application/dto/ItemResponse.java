package com.emazon.stockmicroservice.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ItemResponse {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Long brandId;
    private List<CategorySimpleResponse>categories;

}
