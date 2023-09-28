package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Adress;

public class AdressDTO {
    private long id;
    private String street;
    private Number number;
    private String city;
    private String apartament;
    private long floor;

    public AdressDTO() {
    }

    public AdressDTO(Adress adress) {
        this.street = adress.getStreet();
        this.number = adress.getNumber();
        this.city = adress.getCity();
        this.apartament = adress.getApartament();
        this.floor = adress.getFloor();
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Number getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getApartament() {
        return apartament;
    }

    public long getFloor() {
        return floor;
    }
}
