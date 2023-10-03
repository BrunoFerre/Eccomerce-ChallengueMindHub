package com.example.mate.Eccomerce.dtos;

public class CreateDetailsDTO {

    public long productId;
    public int quantity;

    private CreateDetailsDTO() {

    }
    private CreateDetailsDTO(long productId, int quantity) {
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
