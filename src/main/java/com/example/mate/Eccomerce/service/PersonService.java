package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.models.Person;

public interface PersonService {
    Person findByEmail(String email);
    void save(Person person);

    Person findById(long id);
}
