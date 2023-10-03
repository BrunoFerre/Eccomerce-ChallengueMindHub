package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.models.Adress;

public interface AddressService {
    void save(Adress adress);

    Adress findById(long id);
}
