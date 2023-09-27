package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Person() {
    }

    public Person(String firstname, String lastname, String email, String phone, String password, PersonType personType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = personType;
    }
   /* public void addAdress(Adress adress){
    adress.setUser(this);
    this.adress.add(adress);
    }*/

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
}
