package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.AdressDTO;
import com.example.mate.Eccomerce.models.Adress;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.repositories.AdressRepository;
import com.example.mate.Eccomerce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adress")
public class AdressController {
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/all")
    public List<AdressDTO> getAll(){
        return adressRepository.findAll().stream().map(AdressDTO::new).collect(Collectors.toList());
    }
    @PostMapping("/add")
    public ResponseEntity<Object> newAdress(@RequestBody AdressDTO adressDTO, Authentication authentication){
        if(adressDTO.getApartament().isBlank()){
            return new ResponseEntity<>("Apartament is required", HttpStatus.BAD_REQUEST);
        }
        if(adressDTO.getCity().isBlank()){
            return new ResponseEntity<>("City is required", HttpStatus.NOT_ACCEPTABLE);
        }
        if (adressDTO.getFloor()<=0){
            return new ResponseEntity<>("Floor cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (adressDTO.getNumber()<=0){
            return new ResponseEntity<>("Number cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (adressDTO.getStreet().isBlank()){
            return new ResponseEntity<>("Street is required", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        Adress adress= new Adress(adressDTO.getStreet(),adressDTO.getNumber(),adressDTO.getCity(),adressDTO.getApartament(),adressDTO.getFloor(),true,adressDTO.getZipCode());
        person.addAdress(adress);
        adressRepository.save(adress);
        personRepository.save(person);
        return new ResponseEntity<>("Adress Assigned",HttpStatus.CREATED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAdress(@RequestParam  Long id, Authentication authentication){
        Person person = personRepository.findByEmail(authentication.getName());
        Adress adress=adressRepository.findById(id).orElse(null);
        if (adress==null){
            return new ResponseEntity<>("Adress not found",HttpStatus.NOT_FOUND);
        }
        if (adress.getPerson().getId() != person.getId()){
            return new ResponseEntity<>("Adress not yours",HttpStatus.BAD_REQUEST);
        }
        if (!adress.isStatus()){
            return new ResponseEntity<>("Adress already deleted",HttpStatus.BAD_REQUEST);
        }
        adress.setStatus(false);
        adressRepository.save(adress);
        return new ResponseEntity<>("Adress Deleted",HttpStatus.OK);
    }
}