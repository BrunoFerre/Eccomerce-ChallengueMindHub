package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String body;

    private String userName;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    public Answer() {

    }
    public Answer(String body, String userName){
        this.body = body;
        this.userName = userName;
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
}
