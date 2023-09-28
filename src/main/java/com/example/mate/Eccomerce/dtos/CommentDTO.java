package com.example.mate.Eccomerce.dtos;


import com.example.mate.Eccomerce.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDTO {
    private long id;

    private String body;

    private PersonDTO personDTO;

    private ProductDTO productDTO;

    private boolean active;

    private List<AnswerDTO> answers;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.body = comment.getBody();
        this.personDTO = new PersonDTO(comment.getPerson());
        this.productDTO = new ProductDTO(comment.getProduct());
        this.answers = comment.getAnswers().stream().map(AnswerDTO::new).collect(Collectors.toList());
        this.active = comment.isActive();
    }

    //Getters

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public boolean isActive() {
        return active;
    }
}
