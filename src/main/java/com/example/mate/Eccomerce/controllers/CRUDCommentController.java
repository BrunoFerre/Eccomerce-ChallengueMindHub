package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.CommentDTO;
import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.repositories.PersonRepository;
import com.example.mate.Eccomerce.service.CommentService;
import com.example.mate.Eccomerce.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CRUDCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductService productRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllComments(){
        return new ResponseEntity<>(commentService.getCommentsDTO(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getCommentsByProduct(@RequestParam long id){
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Product product = productRepository.findById(id);
        if (product == null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        List<CommentDTO> comments=product.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

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
        Product product = productRepository.findById(productId);
        if (product==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = new Comment(body, LocalDateTime.now());
        person.addComment(comment);
        commentService.save(comment);
        product.addComment(comment);
        personRepository.save(person);
        productRepository.save(product);
        commentService.save(comment);
        return new ResponseEntity<>("The comment was added", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteComment(@RequestParam long id, Authentication authentication){
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        if (person == null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = commentService.findByIdAndPerson(id, person);
        if (comment == null){
            return new ResponseEntity<>("The comment was not found", HttpStatus.NOT_FOUND);
        }
        comment.setActive(false);
        commentService.save(comment);
        return new ResponseEntity<>("The comment was deleted", HttpStatus.OK);
    }

    @PatchMapping ("/update")
    public ResponseEntity<Object> updateComment(@RequestParam long id, @RequestParam String body, Authentication authentication){
        if (id <= 0){
            return new ResponseEntity<>("The id cannot be less than 1", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        if (person == null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Comment comment = commentService.findByIdAndPerson(id, person);
        if (comment == null){
            return new ResponseEntity<>("The comment was not found", HttpStatus.NOT_FOUND);
        }
        comment.setBody(body);
        comment.setDate(LocalDateTime.now());
        commentService.save(comment);
        return new ResponseEntity<>("The comment was updated", HttpStatus.OK);
    }
}
