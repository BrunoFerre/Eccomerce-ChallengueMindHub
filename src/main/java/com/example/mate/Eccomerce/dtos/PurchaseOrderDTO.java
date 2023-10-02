package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.PurchaseOrder;

import java.time.LocalDateTime;

public class PurchaseOrderDTO {
    private long id;
    private String ticket;
    private double amount;
    private LocalDateTime date;

    public PurchaseOrderDTO() {
    }
    public PurchaseOrderDTO(PurchaseOrder PO){
        this.id = PO.getId();
        this.ticket = PO.getTicket();
        this.amount = PO.getAmount();
        this.date = PO.getDate();
    }

    public long getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
