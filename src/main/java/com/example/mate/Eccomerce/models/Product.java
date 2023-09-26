package com.example.mate.Eccomerce.models;

import com.example.mate.Eccomerce.dtos.CreateProductDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    @OneToOne(fetch = FetchType.EAGER)
    private Punctuation punctuation;

    @OneToMany(mappedBy = "product")
    private List<Details> details;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
    public Product() {

    }
    public Product(String name, String description, double price, int stock, CategoryProduct category, ColorProduct color) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
    }

    public Product(CreateProductDTO createProductDTO) {
        this.name = createProductDTO.getName();
        this.description = createProductDTO.getDescription();
        this.price = createProductDTO.getPrice();
        this.stock = createProductDTO.getStock();
        this.category = createProductDTO.getCategory();
        this.color = createProductDTO.getColor();
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

    public List<Details> getDetails() {
        return details;
    }

    public List<Comment> getComments() {
        return comments;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(CategoryProduct category) {
        this.category = category;
    }

    public void setColor(ColorProduct color) {
        this.color = color;
    }

    public void setPunctuation(Punctuation punctuation) {
        this.punctuation = punctuation;
    }


    //Add
    public void addDetails(Details details) {
        this.details.add(details);
        details.addProduct(this);
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.addProduct(this);
    }
}
