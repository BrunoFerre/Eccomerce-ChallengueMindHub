package com.example.mate.Eccomerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column(length = 10000)
    private String body;

    private LocalDateTime date;

    @OneToMany (mappedBy = "comment")
    private Set<Answer> answers;

    @ManyToOne (fetch = FetchType.EAGER)
    private Person person;

    @ManyToOne (fetch = FetchType.EAGER)
    private Product product;
    private boolean active;
    public Comment() {

    }
    public Comment(String body, LocalDateTime date) {
        this.body = body;
        this.date = date;
        this.active = true;
    }

    //Getters

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDate() {
        return date;
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

    public boolean isActive() {
        return active;
    }
//Setters


    public void setId(long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //Add
    public void addAnswer(Answer answer){
        answer.setComment(this);
        answers.add(answer);
    }
}
