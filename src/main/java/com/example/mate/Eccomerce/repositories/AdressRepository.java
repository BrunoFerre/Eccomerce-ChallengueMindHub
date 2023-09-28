package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdressRepository extends JpaRepository<Adress, Long> {
}
