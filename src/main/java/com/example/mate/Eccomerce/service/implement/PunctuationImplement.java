package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.dtos.PunctuationDTO;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;
import com.example.mate.Eccomerce.repositories.PunctuationRepository;
import com.example.mate.Eccomerce.service.PunctuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PunctuationImplement implements PunctuationService {

    @Autowired
    private PunctuationRepository punctuationRepository;
    @Override
    public void save(Punctuation punctuation) {
        punctuationRepository.save(punctuation);
    }

    @Override
    public void deleteById(long id) {
        punctuationRepository.deleteById(id);
    }

    @Override
    public List<Punctuation> findAll() {
        return punctuationRepository.findAll();
    }

    @Override
    public Punctuation findByProduct(Product product) {
        return punctuationRepository.findByProduct(product);
    }

    @Override
    public PunctuationDTO getDtoById(Punctuation punctuation) {
        return new PunctuationDTO(Objects.requireNonNull(punctuationRepository.findById(punctuation.getId()).orElse(null)));
    }

    @Override
    public Punctuation getById(long id) {
        return punctuationRepository.findById(id).orElse(null);
    }

    @Override
    public PunctuationDTO getDtoById(long id) {
        return new PunctuationDTO(Objects.requireNonNull(punctuationRepository.findById(id).orElse(null)));
    }
}
