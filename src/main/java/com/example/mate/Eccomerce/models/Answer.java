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

    @Column(length = 10000)
    private String body;

    private String userName;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    @ManyToOne (fetch = FetchType.EAGER)
    private Person person;

    private LocalDateTime date;

    private boolean active;
    public Answer() {

    }
    public Answer(String body, String userName,LocalDateTime date){
        this.body = body;
        this.userName = userName;
        this.date=date;
        this.active=true;
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

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public boolean isActive() {
        return active;
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

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
