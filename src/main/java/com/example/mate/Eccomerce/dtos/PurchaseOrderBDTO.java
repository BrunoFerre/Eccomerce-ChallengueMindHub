package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.PaymentMethod;

import java.util.List;

public class PurchaseOrderBDTO {
    private long addressId;
    private PaymentMethod paymentMethod;
    private List<CreateDetailsDTO> details;

    public PurchaseOrderBDTO() {

    }
    public PurchaseOrderBDTO(long addressId,PaymentMethod paymentMethod, List<CreateDetailsDTO> details) {
        this.addressId = addressId;
        this.paymentMethod = paymentMethod;
        this.details = details;
    }

    public long getAddressId() {
        return addressId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<CreateDetailsDTO> getDetails() {
        return details;
    }
}
