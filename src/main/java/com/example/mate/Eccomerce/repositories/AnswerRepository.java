package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByCommentId(long id);

    Answer findByPerson(Person person);
    Answer findByIdAndPerson(long id, Person person);
}
