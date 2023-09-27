package com.example.mate.Eccomerce.service;


import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.Product;

import java.util.Set;

public interface ProductService{
    Set<ProductDTO> findAll();
    Product findById(long id);
    ProductDTO getDtoById(long id);

    Set<ProductDTO> findByCategory(CategoryProduct category);

    void save(Product product);

    void deleteById(long id);

    boolean existsById(long id);
}
