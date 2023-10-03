package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private int quantity;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchaseOrder_id")
    private PurchaseOrder purchaseOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public Details() {
    }

    public Details(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
