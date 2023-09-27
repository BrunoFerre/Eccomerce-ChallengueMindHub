package com.example.mate.Eccomerce.service;


import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.CategoryProduct;

import java.util.Set;

public interface ProductService{
    Set<ProductDTO> findAll();
    ProductDTO findById(long id);

    Set<ProductDTO> findByCategory(CategoryProduct category);
}
