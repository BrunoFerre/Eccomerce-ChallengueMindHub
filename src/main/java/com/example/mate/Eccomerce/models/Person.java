package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private PersonType userType;
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    private Set<Adress> adress = new HashSet<>();
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrder = new HashSet<>();

    public Person() {
    }

    public Person(String firstname, String lastname, String email, String password, PersonType personType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userType = personType;
    }
    public void addAdress(Adress adress){
    adress.setPerson(this);
    this.adress.add(adress);
    }
    public void addPurchaseOrder(PurchaseOrder purchaseOrder){
    purchaseOrder.setPerson(this);
    this.purchaseOrder.add(purchaseOrder);
    }
    public Set<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrder;
    }
   public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonType getUserType() {
        return userType;
    }

    public void setUserType(PersonType personType) {
        this.userType = personType;
    }
    public void addComment(Comment comment){
        comment.setPerson(this);
        this.comments.add(comment);
    }

    public Set<Adress> getAdress() {
        return adress;
    }

    public void setPurchaseOrder(Set<PurchaseOrder> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

}
