package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.repositories.DetailsRepository;
import com.example.mate.Eccomerce.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsImplement implements DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Override
    public void save(Details details) {
        detailsRepository.save(details);
    }
}