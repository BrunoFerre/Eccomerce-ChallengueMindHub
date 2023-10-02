package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.dtos.CommentDTO;
import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.Product;

import java.util.List;

public interface CommentService {
    void save(Comment comment);

    void deleteById(long id);

    List<Comment> findAll();

    List<CommentDTO> getCommentsDTO();

    Comment findCommentByProduct(Product product);

    CommentDTO getCommentDTOByProduct(Product product);

    Comment findById(long id);

    CommentDTO getDtoById(long id);
    Comment findByIdAndPerson(long id, Person person);

    Comment getByIdAndProduct(long id, Product product);

    Comment getCommentsByProduct(long id);
}
