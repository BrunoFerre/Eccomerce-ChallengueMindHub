package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Punctuation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;

    private List<Integer> point;

    private double averagePoints;

    private double actuallyTotalPoints;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Punctuation(){

    }
    public Punctuation(List<Integer> point, Product product){
        this.point = point;
        this.product = product;
        this.actuallyTotalPoints = totalAveragePoints(point);
        this.averagePoints=totalAveragePoints(point);
    }

    //Methods
    public double totalAveragePoints(List<Integer> point){
        return totalPoints(point)/point.size();
    }
    public double totalPoints(List<Integer> points){
        double aux=0;
        for (int i = 0; i < points.size(); i++) {
            aux += points.get(i);
        }
        return aux;
    }

    //Getters

    public long getId() {
        return id;
    }
    public List<Integer> getPoint() {
        return point;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public double getActuallyTotalPoints() {
        return actuallyTotalPoints;
    }

    public Product getProduct() {
        return product;
    }

    //Setters

    public void setPoint(List<Integer> point) {
        this.point = point;
    }

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public void setActuallyTotalPoints(double actuallyTotalPoints) {
        this.actuallyTotalPoints = actuallyTotalPoints;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
