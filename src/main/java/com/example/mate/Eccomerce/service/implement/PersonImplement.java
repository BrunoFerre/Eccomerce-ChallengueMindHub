package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.repositories.PersonRepository;
import com.example.mate.Eccomerce.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonImplement implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person findById(long id) {
        return personRepository.findById(id).orElse(null);
    }
}
