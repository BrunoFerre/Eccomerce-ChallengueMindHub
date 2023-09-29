package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByProduct(Product product);

    Comment findByIdAndPerson(long id, Person person);
    Comment findByIdAndProduct(long id, Product product);

    Comment getCommentsByProduct(long id);
}
