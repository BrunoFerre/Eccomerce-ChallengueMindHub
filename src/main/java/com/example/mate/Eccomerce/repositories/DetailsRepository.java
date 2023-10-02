package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
