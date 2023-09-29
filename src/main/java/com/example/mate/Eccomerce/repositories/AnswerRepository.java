package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByCommentId(long id);

    Answer findByPerson(Person person);
    Answer findByIdAndPerson(long id, Person person);
}
