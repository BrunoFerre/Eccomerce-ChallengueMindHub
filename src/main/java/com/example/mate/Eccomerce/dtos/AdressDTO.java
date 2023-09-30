package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Adress;
import com.example.mate.Eccomerce.models.Person;

public class AdressDTO {
    private long id;
    private String street;
    private long number;
    private String city;
    private String apartament;
    private long floor;

    private boolean active;
    private Person person;

    public AdressDTO() {
    }

    public AdressDTO(Adress adress) {
        this.id = adress.getId();
        this.street = adress.getStreet();
        this.number = adress.getNumber();
        this.city = adress.getCity();
        this.apartament = adress.getApartament();
        this.floor = adress.getFloor();
        this.active = adress.isStatus();
    }

    public AdressDTO(String street, long number, String city, String apartament, long floor) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.apartament = apartament;
        this.floor = floor;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public long getNumber() {
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

    public boolean isActive() {
        return active;
    }

    public Person getPerson() {
        return person;
    }
}
