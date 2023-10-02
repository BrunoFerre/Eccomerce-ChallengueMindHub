package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.service.ProductService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/punctuations")
public class AddPunctuationController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Object> addPunctuation(@RequestParam long id, @RequestParam int point, Authentication authentication){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (point<0){
            return new ResponseEntity<>("The point cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Product product=productService.findById(id);
        if (product==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        product.addPoint(point);
        productService.save(product);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

}
