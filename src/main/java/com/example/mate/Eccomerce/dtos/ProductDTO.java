package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.ColorProduct;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {

    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    private Punctuation punctuation;

    /*private List<DetailsDTO> details;*/

    private List<CommentDTO> comments;

    public ProductDTO() {

    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.category = product.getCategory();
        this.color = product.getColor();
        this.punctuation = product.getPunctuation();
       /* this.details = product.getDetails().stream().map(DetailsDTO::new).collect(Collectors.toList());
        this.comments = product.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());*/
    }

    //Getters

    public long getId() {
        return id;
    }

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

    public Punctuation getPunctuation() {
        return punctuation;
    }

  /*  public List<DetailsDTO> getDetails() {
        return details;
    }*/

    public List<CommentDTO> getComments() {
        return comments;
    }
}
