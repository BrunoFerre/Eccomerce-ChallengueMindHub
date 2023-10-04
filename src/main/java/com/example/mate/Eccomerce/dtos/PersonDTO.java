package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PersonType;

import java.util.Set;
import java.util.stream.Collectors;

public class PersonDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private PersonType personType;

    private String password;

    private Set<AdressDTO> address;

    private Set<PurchaseOrderDTO> purchaseOrder;
    public PersonDTO() {

    }
    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.personType = person.getUserType();
        this.address = person.getAdress().stream().map(AdressDTO::new).collect(Collectors.toSet());
        this.purchaseOrder = person.getPurchaseOrder().stream().map(PurchaseOrderDTO::new).collect(Collectors.toSet());
    }

    public PersonDTO(String firstname, String lastname, String email,String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public PersonType getUserType() {
        return personType;
    }

    public String getPassword() {
        return password;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public Set<AdressDTO> getAddress() {
        return address;
    }

    public Set<PurchaseOrderDTO> getPurchaseOrder() {
        return purchaseOrder;
    }
}
