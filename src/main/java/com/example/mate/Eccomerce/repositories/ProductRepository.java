package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;
@RepositoryRestResource

public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findByCategory(CategoryProduct category);
}
