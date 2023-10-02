package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.ColorProduct;

import java.util.List;

public class CreateProductDTO {
    private String name;

    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    private double discount;

    private String image;

    private List<String> imageCollection;

    public CreateProductDTO(){}

    public CreateProductDTO(String name, String description, double price, int stock, CategoryProduct category, ColorProduct color, double discount, String image, List<String> imageCollection) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
        this.discount = discount;
        this.image = image;
        this.imageCollection = imageCollection;
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

    public double getDiscount() {
        return discount;
    }

    public String getImage() {
        return image;
    }

    public List<String> getImageCollection() {
        return imageCollection;
    }
}
