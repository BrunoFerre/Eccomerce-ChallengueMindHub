package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.ColorProduct;
import com.example.mate.Eccomerce.models.Punctuation;

public class CreateProductDTO {
    private String name;

    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    public CreateProductDTO(){}

    public CreateProductDTO(String name, String description, double price, int stock, CategoryProduct category, ColorProduct color) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public CategoryProduct getCategory() {
        return category;
    }

    public ColorProduct getColor() {
        return color;
    }
}
