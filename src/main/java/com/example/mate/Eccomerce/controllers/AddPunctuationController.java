package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.Punctuation;
import com.example.mate.Eccomerce.service.ProductService;
import com.example.mate.Eccomerce.service.PunctuationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/punctuation")
public class AddPunctuationController {
    @Autowired
    private PunctuationService punctuationService;
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Object> addPunctuation(@RequestParam int point, @RequestParam long id, Authentication authentication){
        if (point<=0){
            return new ResponseEntity<>("The point cannot be less than 0", HttpStatus.BAD_REQUEST);
        }
        if (id<=0){
            return new ResponseEntity<>("The id cannot be less than 0", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        Product product = productService.findById(id);
        if (person==null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Punctuation punctuation = punctuationService.findByProduct(product);
        if (punctuation==null){
            return new ResponseEntity<>("The punctuation was not found", HttpStatus.NOT_FOUND);
        }
        punctuation.addPoint(point);
        punctuationService.save(punctuation);
        return new ResponseEntity<>("The punctuation was added", HttpStatus.OK);
    }
}
