package com.example.mate.Eccomerce.dtos;


import com.example.mate.Eccomerce.models.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDTO {
    private long id;

    private String body;

    private boolean active;

    private LocalDateTime date;

    private List<AnswerDTO> answers;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.body = comment.getBody();
        this.date = comment.getDate();
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

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
