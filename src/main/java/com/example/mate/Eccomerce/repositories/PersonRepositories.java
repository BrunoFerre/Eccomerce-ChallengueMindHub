package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface PersonRepositories extends JpaRepository<Person, Long> {
    List<Person> findAll();
    boolean existsByEmail(String email);
}
