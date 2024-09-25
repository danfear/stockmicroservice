package com.emazon.stockmicroservice.domain.model;

import java.util.List;

public class Item {
    private final Long id;
    private final String name;
    private final String description;
    private final int quantity;
    private final double price;
    private final Brand brand;
    private final List<Category> categories;

    public Item(Long id, String name, String description, int quantity, double price, Brand brand, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public Brand getBrand() { return brand; }
    public List<Category> getCategories() { return categories; }
}
