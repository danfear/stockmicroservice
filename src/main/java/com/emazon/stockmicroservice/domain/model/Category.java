package com.emazon.stockmicroservice.domain.model;

public class Category {
    public final Long id;
    public final String name;
    public final String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
