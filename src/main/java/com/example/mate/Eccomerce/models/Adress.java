package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private String street;
    private Number number;
    private String city;
    private String apartament;
    private long floor;
  /*  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;*/

    public Adress(long id, String street, Number number, String city, String apartament, long floor) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.apartament = apartament;
        this.floor = floor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApartament() {
        return apartament;
    }

    public void setApartament(String apartament) {
        this.apartament = apartament;
    }

    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/
}
