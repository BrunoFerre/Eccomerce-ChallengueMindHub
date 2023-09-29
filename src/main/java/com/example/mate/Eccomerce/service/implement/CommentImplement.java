package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.dtos.CommentDTO;
import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.repositories.CommentRepository;
import com.example.mate.Eccomerce.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentImplement implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<CommentDTO> getCommentsDTO() {
        return commentRepository.findAll().stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    public Comment findCommentByProduct(Product product) {
        return commentRepository.findByProduct(product);
    }

    @Override
    public CommentDTO getCommentDTOByProduct(Product product) {
        return new CommentDTO(Objects.requireNonNull(commentRepository.findByProduct(product)));
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public CommentDTO getDtoById(long id) {
        return new CommentDTO(Objects.requireNonNull(commentRepository.findById(id).orElse(null)));
    }

    @Override
    public Comment findByIdAndPerson(long id, Person person) {
        return commentRepository.findByIdAndPerson(id, person);
    }

    @Override
    public Comment getByIdAndProduct(long id, Product product) {
        return commentRepository.findByIdAndProduct(id, product);
    }

    @Override
    public Comment getCommentsByProduct(long id) {
        return commentRepository.getCommentsByProduct(id);
    }
}
