package com.example.mate.Eccomerce.models;

import com.example.mate.Eccomerce.dtos.CreateProductDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private String name;

    @Column(length = 10000)
    private String description;

    private double price;

    private int stock;

    private CategoryProduct category;

    private ColorProduct color;

    private double discount;

    private String image;
    
    private double averagePoints;

    private double actuallyTotalPoints;

    @ElementCollection
    private List<String> imageCollection = new ArrayList<>();

    @ElementCollection
    private List<Integer> points = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private Set<Details> details = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments= new ArrayList<>();
    public Product() {

    }
    public Product(String name, String description, double price, int stock, CategoryProduct category, ColorProduct color, double discount, String image,List<String> imageCollection) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.color = color;
        this.discount = discount;
        this.image = image;
        this.imageCollection = imageCollection;
        this.points.add(0);
    }

    public Product(CreateProductDTO createProductDTO) {
        this.name = createProductDTO.getName();
        this.description = createProductDTO.getDescription();
        this.price = createProductDTO.getPrice();
        this.stock = createProductDTO.getStock();
        this.category = createProductDTO.getCategory();
        this.color = createProductDTO.getColor();
        this.discount = createProductDTO.getDiscount();
        this.image= createProductDTO.getImage();
        this.imageCollection = createProductDTO.getImageCollection();
        this.points.add(0);
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

    public List<Integer> getPoint() {
        return points;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public double getActuallyTotalPoints() {
        return actuallyTotalPoints;
    }

    public String getImage() {
        return image;
    }

    public Set<Details> getDetails() {
        return details;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public double getDiscount() {
        return discount;
    }

    public List<String> getImageCollection() {
        return imageCollection;
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

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public void setActuallyTotalPoints(double actuallyTotalPoints) {
        this.actuallyTotalPoints = actuallyTotalPoints;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setImageCollection(List<String> imageCollection) {
        this.imageCollection = imageCollection;
    }

    //Add
    public void addDetails(Details detail) {
        detail.setProduct(this);
        details.add(detail);
    }
    public void addComment(Comment comment) {
        comment.setProduct(this);
        comments.add(comment);
    }

    public void addPoint(int point) {
        this.points.add(point);
        this.actuallyTotalPoints = this.totalPoints(this.points);
        this.averagePoints = totalAveragePoints(this.actuallyTotalPoints);
    }

    //Methods
    public double totalAveragePoints(double actuallyTotalPoints){
        return actuallyTotalPoints/(double) this.points.size()-1;
    }

    public double totalPoints(List<Integer> points){
        double aux = 0;
        for (int point : points) {
            aux += point;
        }
        return aux;
    }


}
