package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findByCategory(CategoryProduct category);
}
