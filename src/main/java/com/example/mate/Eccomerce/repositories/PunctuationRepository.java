package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PunctuationRepository extends JpaRepository<Punctuation,Long> {
    Punctuation findByProduct(Product product);
}
