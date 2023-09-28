package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comments")
public class CUDCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/add")
    public ResponseEntity <Object> addComment(@RequestParam String body, @RequestParam long productId, Authentication authentication){
        if(body.isBlank()){
            return new ResponseEntity<>("The body cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (productId <= 0){
            return new ResponseEntity<>("The product id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        if (person==null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = new Comment(body, LocalDateTime.now());
        person.addComment(comment);
        personRepository.save(person);
        commentService.save(comment);
        return new ResponseEntity<>("The comment was added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable long id, Authentication authentication){
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        if (person == null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = commentService.getByIdAndPerson(id, person);
        if (comment == null){
            return new ResponseEntity<>("The comment was not found", HttpStatus.NOT_FOUND);
        }
        comment.setActive(false);
        commentService.save(comment);
        return new ResponseEntity<>("The comment was deleted", HttpStatus.OK);
    }

    @PatchMapping ("/update/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable long id, @RequestParam String body, Authentication authentication){
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        if (person == null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = commentService.getByIdAndPerson(id, person);
        if (comment == null){
            return new ResponseEntity<>("The comment was not found", HttpStatus.NOT_FOUND);
        }
        comment.setBody(body);
        comment.setDate(LocalDateTime.now());
        commentService.save(comment);
        return new ResponseEntity<>("The comment was updated", HttpStatus.OK);
    }
}
