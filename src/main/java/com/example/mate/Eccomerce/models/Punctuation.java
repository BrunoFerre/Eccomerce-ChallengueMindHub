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

    @ElementCollection
    @CollectionTable(name = "punctuation_points", joinColumns = @JoinColumn(name = "punctuation_id"))
    private List<Integer> points;

    private double averagePoints;

    private double actuallyTotalPoints;

    @OneToOne
    private Product product;

    public Punctuation(){

    }
    public Punctuation(Product product){
        this.points.add(0);
        this.product = product;
        this.actuallyTotalPoints = totalAveragePoints(points);
        this.averagePoints=totalAveragePoints(points);
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
        return points;
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
        this.points = point;
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
