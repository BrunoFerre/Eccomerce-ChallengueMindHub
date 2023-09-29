package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Product;

import javax.persistence.*;
import java.util.List;

public class AnswerDTO {
    private long id;

    private String body;

    private String userName;
    private CommentDTO comment;

    private boolean active;

    public AnswerDTO(){

    }
    public AnswerDTO(Answer answer){
        this.id = answer.getId();
        this.body = answer.getBody();
        this.userName = answer.getUserName();
        this.comment = new CommentDTO(answer.getComment());
        this.active = answer.isActive();
    }

    //Getters

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getUserName() {
        return userName;
    }

    public CommentDTO getComment() {
        return comment;
    }
    public boolean isActive() {
        return active;
    }
}
