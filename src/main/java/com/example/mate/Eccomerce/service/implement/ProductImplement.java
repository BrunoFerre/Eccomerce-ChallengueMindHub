package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.repositories.ProductRepository;
import com.example.mate.Eccomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductImplement implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Set<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ProductDTO findById(long id) {
        return new ProductDTO(Objects.requireNonNull(productRepository.findById(id).orElse(null)));
    }

    @Override
    public Set<ProductDTO> findByCategory(CategoryProduct category) {
        return productRepository.findByCategory(category).stream().map(ProductDTO::new).collect(Collectors.toSet());
    }
}
