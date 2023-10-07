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
    private PaymentMethod paymentMethod;
    @OneToOne
    @JoinColumn(name = "id_adress")
    private Adress adress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Person person;
    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
    private Set<Details> details = new HashSet<>();

    public PurchaseOrder() {
    }

    public PurchaseOrder( double amount, LocalDateTime date, PaymentMethod paymentMethod,Adress adress, String ticket) {
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.adress = adress;
        this.ticket = ticket;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void addDetails(Details details) {
        details.setPurchaseOrder(this);
        this.details.add(details);
    }

    public Set<Details> getDetails() {
        return details;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
