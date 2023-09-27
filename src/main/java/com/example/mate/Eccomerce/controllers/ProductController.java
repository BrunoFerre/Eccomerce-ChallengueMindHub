package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<Object> getALlProducts() {
        Set<ProductDTO> products= productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>("There was an error while fetching the products.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable long id){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        ProductDTO productDTO=productService.findById(id);
        if (productDTO==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @GetMapping("/products/type/{category}")
    public ResponseEntity<Object> getProductByCategory(@PathVariable CategoryProduct category){
        try{
            Set<ProductDTO> products= productService.findByCategory(category);
            if (products.isEmpty()){
                return new ResponseEntity<>("The products was not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("The category was not found",HttpStatus.BAD_REQUEST);
        }
    }
}
