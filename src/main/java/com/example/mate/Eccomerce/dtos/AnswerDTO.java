package com.example.mate.Eccomerce.dtos;

import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class AnswerDTO {
    private long id;

    private String body;

    private String userName;

    private boolean active;

    private LocalDateTime date;

    public AnswerDTO(){

    }
    public AnswerDTO(Answer answer){
        this.id = answer.getId();
        this.body = answer.getBody();
        this.userName = answer.getUserName();
        this.active = answer.isActive();
        this.date=answer.getDate();
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

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
