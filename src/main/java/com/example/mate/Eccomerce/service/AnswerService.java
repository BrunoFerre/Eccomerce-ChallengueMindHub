package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.dtos.AnswerDTO;
import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Person;

import java.util.List;

public interface AnswerService {
    void save(Answer answer);
    void deleteById(long id);

    List<AnswerDTO> getAllAnswerDTO();
    Answer findById(long id);

    List<AnswerDTO> getAnswersDTOByComment(long id);

    List<Answer> findAll();

    List<Answer> findByCommentId(long id);

    AnswerDTO getDtoById(long id);

    Answer getByPerson(Person person);
    Answer getByIdAndPerson(long id, Person person);
}
