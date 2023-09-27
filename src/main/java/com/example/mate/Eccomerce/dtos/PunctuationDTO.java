package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;

import javax.persistence.OneToOne;
import java.util.List;

public class PunctuationDTO {
    private long id;

    private List<Integer> point;

    private double averagePoints;

    private double actuallyTotalPoints;

    private Product product;

    public PunctuationDTO() {

    }
    public PunctuationDTO(Punctuation punctuation) {
        this.id = punctuation.getId();
        this.point = punctuation.getPoint();
        this.product = punctuation.getProduct();
        this.averagePoints = punctuation.getAveragePoints();
        this.actuallyTotalPoints = punctuation.getActuallyTotalPoints();
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
}
