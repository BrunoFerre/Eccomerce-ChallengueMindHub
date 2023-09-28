package com.example.mate.Eccomerce.dtos;


import com.example.mate.Eccomerce.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDTO {
    private long id;

    private String body;

    private UserDTO userDTO;

    private ProductDTO productDTO;

    private List<AnswerDTO> answers;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.body = comment.getBody();
        this.userDTO = new UserDTO(comment.getUser());
        this.productDTO = new ProductDTO(comment.getProduct());
        this.answers = comment.getAnswers().stream().map(AnswerDTO::new).collect(Collectors.toList());
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }
}
