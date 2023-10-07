package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.PersonDTO;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PersonType;
import com.example.mate.Eccomerce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @GetMapping("/current")
    public PersonDTO getCurrent(Authentication authentication){
        Person person = personRepository.findByEmail(authentication.getName());
        return new PersonDTO(person);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id, Authentication authentication){
        if (authentication == null){
            return new ResponseEntity<>("Error1", HttpStatus.FORBIDDEN);
        }
        Person person = personRepository.findById(id).orElse(null);
        if (person == null){
            return new ResponseEntity<>("Error2", HttpStatus.FORBIDDEN);
        }
        PersonDTO personDTO = new PersonDTO(person);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody PersonDTO personDTO) {

        if (personDTO.getEmail().isBlank()) {
            return new ResponseEntity<>("Email is invalid or required", HttpStatus.FAILED_DEPENDENCY);
        }
        if (personDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("Password is invalid or required", HttpStatus.FORBIDDEN);
        }

        if (personDTO.getFirstname().isBlank()) {
            return new ResponseEntity<>("Firstname is required", HttpStatus.NOT_ACCEPTABLE);
        }
        if (personDTO.getLastname().isBlank()) {
            return new ResponseEntity<>("Lastname is required", HttpStatus.BAD_GATEWAY);
        }
        if (personRepository.existsByEmail(personDTO.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        }

        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getEmail(), passwordEncoder.encode(personDTO.getPassword()), PersonType.CLIENT);
        personRepository.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Object> addAdmin(@RequestBody PersonDTO personDTO){
        if (personDTO.getFirstname().isBlank()){
            return new ResponseEntity<>("Firstname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getLastname().isBlank()){
            return new ResponseEntity<>("Lastname is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getEmail().isBlank()){
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }
        if (personRepository.existsByEmail(personDTO.getEmail())){
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPhone().isBlank()){
            return new ResponseEntity<>("Phone is required", HttpStatus.BAD_REQUEST);
        }
        if (personDTO.getPassword().isBlank()){
            return new ResponseEntity<>("Password is required", HttpStatus.BAD_REQUEST);
        }
        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getEmail(),personDTO.getPassword(), PersonType.ADMIN);
        personRepository.save(person);
        return new ResponseEntity<>("Welcome to the admin", HttpStatus.CREATED);
    }
}
