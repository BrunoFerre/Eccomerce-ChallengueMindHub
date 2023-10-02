package com.example.mate.Eccomerce.dtos;

public class CreatePurchaseOrderDTO {

    public long productId;
    public int quantity;

    private CreatePurchaseOrderDTO() {

    }
    private CreatePurchaseOrderDTO(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
