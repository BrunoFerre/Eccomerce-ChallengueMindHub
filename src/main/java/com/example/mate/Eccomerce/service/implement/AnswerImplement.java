package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.dtos.AnswerDTO;
import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.repositories.AnswerRepository;
import com.example.mate.Eccomerce.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnswerImplement implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void deleteById(long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<AnswerDTO> getAllAnswerDTO() {
        return answerRepository.findAll().stream().map(AnswerDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnswerDTO> getAnswersDTOByComment(long id) {
        return answerRepository.findByCommentId(id).stream().map(AnswerDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Answer> findByCommentId(long id) {
        return answerRepository.findByCommentId(id);
    }

    @Override
    public AnswerDTO getDtoById(long id) {
        return new AnswerDTO(Objects.requireNonNull(answerRepository.findById(id).orElse(null)));
    }

    @Override
    public Answer getByPerson(Person person) {
        return answerRepository.findByPerson(person);
    }

    @Override
    public Answer getByIdAndPerson(long id, Person person) {
        return answerRepository.findByIdAndPerson(id, person);
    }
}
