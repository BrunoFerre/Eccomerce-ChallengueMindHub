package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PersonType;

public class PersonDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private PersonType personType;
    private String password;

    public PersonDTO() {

    }
    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.password = person.getPassword();
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
}
