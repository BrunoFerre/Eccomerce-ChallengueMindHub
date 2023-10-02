package com.example.mate.Eccomerce.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderBuyDTO {
    private long id;
    private LocalDateTime orderDate;
    private List<ProductDTO> products;
    private String client;
    private String paymentMethod;
    private double totalAmountToPay;
    private AdressDTO adress;

    public PurchaseOrderBuyDTO(LocalDateTime orderDate, List<ProductDTO> products, String client, String paymentMethod, double totalAmountToPay, AdressDTO adress) {
        this.orderDate = orderDate;
        this.products = products;
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.totalAmountToPay = totalAmountToPay;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public String getClient() {
        return client;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public AdressDTO getAdress() {
        return adress;
    }
}
