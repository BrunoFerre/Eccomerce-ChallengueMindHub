package com.example.mate.Eccomerce.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String ticket;
    private double amount;
    private LocalDateTime date;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Person person;
    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
    private Set<Details> details = new HashSet<>();

    public PurchaseOrder() {
    }

    public PurchaseOrder(String ticket, double amount, LocalDateTime date) {
        this.ticket = ticket;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Details> getDetails() {
        return details;
    }

    public void setDetails(Set<Details> details) {
        this.details = details;
    }
}
