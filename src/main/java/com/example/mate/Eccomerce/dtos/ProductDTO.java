package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.ColorProduct;
import com.example.mate.Eccomerce.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {

    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    private double discount;

    private double averagePoints;

    private double actuallyTotalPoints;

    private String image;

    private List<String> imageCollection;

    private List<Integer> points;

    private List<DetailsDTO> details;

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
        this.image = product.getImage();
        this.imageCollection= product.getImageCollection();
        this.discount = product.getDiscount();
        this.actuallyTotalPoints = product.getActuallyTotalPoints();
        this.averagePoints = product.getAveragePoints();
        this.points = product.getPoint();
        this.details = product.getDetails().stream().map(DetailsDTO::new).collect(Collectors.toList());
        this.comments = product.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
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

  public List<DetailsDTO> getDetails() {
        return details;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }
    public double getDiscount() {
        return discount;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public double getActuallyTotalPoints() {
        return actuallyTotalPoints;
    }

    public List<Integer> getPoints() {
        return points;
    }
    public String getImage() {
        return image;
    }

    public List<String> getImageCollection() {
        return imageCollection;
    }
}
