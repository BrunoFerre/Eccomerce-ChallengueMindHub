package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.dtos.PunctuationDTO;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;

import java.util.List;

public interface PunctuationService {
    void save(Punctuation punctuation);
    void deleteById(long id);
    List<Punctuation> findAll();
    Punctuation findByProduct(Product product);
    PunctuationDTO getDtoById(Punctuation punctuation);
    Punctuation getById(long id);
    PunctuationDTO getDtoById(long id);
}
