package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.PurchaseOrder;


public class DetailsDTO {
    private long id;
    private double quantity;
    private double price;

    private PurchaseOrderDTO purchaseOrder;
    private ProductDTO product;
    public DetailsDTO() {
    }

    public DetailsDTO(Details details) {
        this.id = details.getId();
        this.quantity = details.getQuantity();
        this.price = details.getPrice();
    }

    public long getId() {
        return id;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public PurchaseOrderDTO getPurchaseOrder() {
        return purchaseOrder;
    }
    public ProductDTO getProduct() {
        return product;
    }
}