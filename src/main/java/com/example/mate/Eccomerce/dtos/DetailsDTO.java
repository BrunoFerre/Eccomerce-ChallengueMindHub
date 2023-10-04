package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.PurchaseOrder;


public class DetailsDTO {
    private long id;
    private int quantity;
    private double price;
    private long productID;
    public DetailsDTO() {
    }

    public DetailsDTO(Details details) {
        this.id = details.getId();
        this.quantity = details.getQuantity();
        this.price = details.getPrice();
        this.productID = details.getProduct().getId();
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public long getProductID() {
        return productID;
    }

}