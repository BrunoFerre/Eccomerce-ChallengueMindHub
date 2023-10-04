package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.models.Adress;
import com.example.mate.Eccomerce.repositories.AdressRepository;
import com.example.mate.Eccomerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressImplement implements AddressService {

    @Autowired
    private AdressRepository adressRepository;


    @Override
    public void save(Adress adress) {
        adressRepository.save(adress);
    }

    @Override
    public Adress findById(long id) {
        return adressRepository.findById(id).orElse(null);
    }
}
