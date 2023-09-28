package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.repositories.PersonRepositories;
import com.example.mate.Eccomerce.service.AnswerService;
import com.example.mate.Eccomerce.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/answers")
public class CUDAnswerController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private AnswerService  answerService;

    @Autowired
    private PersonRepositories personRepository;

    @PostMapping("add")
    public ResponseEntity<Object> addAnswer(@RequestParam String answer, @RequestParam long commentId, Authentication authentication){
        if (answer.isBlank()){
            return new ResponseEntity<>("The answer cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (commentId<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (authentication==null){
            return new ResponseEntity<>("The user is not authenticated", HttpStatus.FORBIDDEN);
        }
        Person person= personRepository.findByEmail(authentication.getName());
        Comment comment= commentService.findById(commentId);
        if (comment==null){
            return new ResponseEntity<>("The comment was not found", HttpStatus.NOT_FOUND);
        }
        if (person== null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Answer answer1= new Answer(answer, person.getFirstname()+" "+person.getLastname(), LocalDateTime.now());
        comment.addAnswer(answer1);
        commentService.save(comment);
        return new ResponseEntity<>("The answer was added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAnswer(@PathVariable long id, Authentication authentication){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Person person= personRepository.findByEmail(authentication.getName());
        if (person== null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Answer answer= answerService.getByIdAndPerson(id, person);
        if (answer==null){
            return new ResponseEntity<>("The answer was not found", HttpStatus.NOT_FOUND);
        }
        answer.setActive(false);
        answerService.save(answer);
        return new ResponseEntity<>("The answer was deleted", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateAnswer(@PathVariable long id, @RequestParam String answer, Authentication authentication){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Person person= personRepository.findByEmail(authentication.getName());
        if (person== null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Answer answer1= answerService.getByIdAndPerson(id,person);
        if (answer1==null){
            return new ResponseEntity<>("The answer was not found", HttpStatus.NOT_FOUND);
        }
        answer1.setBody(answer);
        answer1.setDate(LocalDateTime.now());
        answerService.save(answer1);
        return new ResponseEntity<>("The answer was updated", HttpStatus.OK);
    }
}
