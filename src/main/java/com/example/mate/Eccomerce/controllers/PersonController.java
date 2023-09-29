package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.PersonDTO;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PersonType;
import com.example.mate.Eccomerce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/all")
    public List<PersonDTO> getAll(){
        return personRepository.findAll().stream().map(PersonDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable long id){
        return new PersonDTO(personRepository.findById(id).orElse(null));
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PersonDTO personDTO){
        if (personDTO.getFirstname().isBlank()){
            return new ResponseEntity<>("firstname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getLastname().isBlank()){
            return new ResponseEntity<>("lastname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getEmail().isBlank()){
            return new ResponseEntity<>("email is required", HttpStatus.BAD_REQUEST);
        }
        if (personRepository.existsByEmail(personDTO.getEmail())){
            return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPhone().isBlank()){
            return new ResponseEntity<>("phone is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPassword().isBlank()){
            return new ResponseEntity<>("password is required", HttpStatus.BAD_REQUEST);
        }
        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getEmail(), personDTO.getPhone(), passwordEncoder.encode(personDTO.getPassword()), PersonType.CLIENT);
        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/addAdmin")
    public ResponseEntity<Object> addAdmin(@RequestBody PersonDTO personDTO){
        if (personDTO.getFirstname().isBlank()){
            return new ResponseEntity<>("firstname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getLastname().isBlank()){
            return new ResponseEntity<>("lastname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getEmail().isBlank()){
            return new ResponseEntity<>("email is required", HttpStatus.BAD_REQUEST);
        }
        if (personRepository.existsByEmail(personDTO.getEmail())){
            return new ResponseEntity<>("email already exists", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPhone().isBlank()){
            return new ResponseEntity<>("phone is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPassword().isBlank()){
            return new ResponseEntity<>("password is required", HttpStatus.BAD_REQUEST);
        }
        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getEmail(), personDTO.getPhone(), personDTO.getPassword(), PersonType.ADMIN);
        personRepository.save(person);
        return new ResponseEntity<>("Welcome to the admin", HttpStatus.CREATED);
    }
}
