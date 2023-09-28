package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.PurchaseOrder;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DetailsDTO {
    private long id;
    private double quantity;
    private double price;
    private PurchaseOrderDTO purchaseOrderDTO;
    private ProductDTO productDTO;

    public DetailsDTO() {
    }
    public DetailsDTO(Details details){
        this.id = details.getId();
        this.quantity = details.getQuantity();
        this.price = details.getPrice();
        this.purchaseOrderDTO = new PurchaseOrderDTO(details.getPurchaseOrder());
        this.productDTO = new ProductDTO(details.getProduct());
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
        return purchaseOrderDTO;
    }

    public ProductDTO getProduct() {
        return productDTO;
    }
}
