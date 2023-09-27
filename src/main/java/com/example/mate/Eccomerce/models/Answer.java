package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String body;

    private String userName;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    public Answer() {

    }
    public Answer(String body, String userName, LocalDateTime date){
        this.body = body;
        this.userName = userName;
        this.date = date;
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

    public Comment getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    //Setters

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }
}
