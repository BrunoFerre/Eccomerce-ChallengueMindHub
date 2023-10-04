package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Adress;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.models.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseOrderDTO {
    private long id;
    private double amount;
    private LocalDateTime date;
    private String ticket;
    private PaymentMethod paymentMethod;

    private long personID;

    private List<DetailsDTO> details;

    private AdressDTO adress;
    public PurchaseOrderDTO() {
    }
    public PurchaseOrderDTO(PurchaseOrder PO) {
        this.id = PO.getId();
        this.amount = PO.getAmount();
        this.date = PO.getDate();
        this.paymentMethod = PO.getPaymentMethod();
        this.details = PO.getDetails().stream().map(DetailsDTO::new).collect(Collectors.toList());
        this.ticket = PO.getTicket();
        this.adress = new AdressDTO(PO.getAdress()); ;
        this.personID = PO.getPerson().getId();
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public long getPersonID() {
        return personID;
    }

    public String getTicket() {
        return ticket;
    }

    public List<DetailsDTO> getDetails() {
        return details;
    }
    public AdressDTO getAdress() {
        return adress;
    }
}
