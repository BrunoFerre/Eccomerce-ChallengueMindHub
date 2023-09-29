package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByCommentId(long id);
}
