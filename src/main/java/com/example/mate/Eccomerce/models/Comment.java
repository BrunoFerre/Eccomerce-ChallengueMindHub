package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String body;

    private LocalDateTime date;

    @OneToMany (mappedBy = "comment", fetch = FetchType.EAGER)
    private Set<Answer> answers= new HashSet<>();

    @ManyToOne (fetch = FetchType.EAGER)
    private Person person;

    @ManyToOne (fetch = FetchType.EAGER)
    private Product product;

    public Comment() {

    }
    public Comment(String body, LocalDateTime date) {
        this.body = body;
        this.date = date;
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Person getPerson() {
        return person;
    }
    public Product getProduct() {
        return product;
    }
    public LocalDateTime getDate() {
        return date;
    }

    //Setters

    public void setBody(String body) {
        this.body = body;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public void setUser(Person person) {
        this.person = person;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    //Add
    public void addAnswer(Answer answer){
        answer.setComment(this);
        answers.add(answer);
    }
}
