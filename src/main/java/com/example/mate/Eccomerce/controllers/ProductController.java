package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.CreateProductDTO;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.Answer;
import com.example.mate.Eccomerce.models.CategoryProduct;
import com.example.mate.Eccomerce.models.Comment;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.service.AnswerService;
import com.example.mate.Eccomerce.service.CommentService;
import com.example.mate.Eccomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AnswerService answerService;

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
        ProductDTO productDTO=productService.getDtoById(id);
        if (productDTO==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @GetMapping("/products/{category}")
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

    @PatchMapping("/products/{id}/discount")
    public ResponseEntity<Object> updateDiscount(@PathVariable long id,@RequestParam double discount){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (discount<=0){
            return new ResponseEntity<>("The discount cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Product product=productService.findById(id);
        if (product==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        product.setDiscount(discount);
        productService.save(product);
        ProductDTO productDTO=productService.getDtoById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
    @PatchMapping("/products/{id}/Stock")
    public ResponseEntity<Object> updateStock(@PathVariable long id,@RequestParam int stock){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (stock<=0){
            return new ResponseEntity<>("The stock cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Product product=productService.findById(id);
        if (product==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        product.setStock(stock);
        productService.save(product);
        ProductDTO productDTO=productService.getDtoById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
    @PatchMapping("/products/{id}/price")
    public ResponseEntity<Object> updatePrice(@PathVariable long id,@RequestParam double price){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (price<=0){
            return new ResponseEntity<>("The price cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        Product product=productService.findById(id);
        if (product==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        product.setPrice(price);
        productService.save(product);
        ProductDTO productDTO=productService.getDtoById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDTO createProductDTO){
        if (createProductDTO.getName().isBlank()){
            return new ResponseEntity<>("The name cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getDescription().isBlank()){
            return new ResponseEntity<>("The description cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getPrice()<=0){
            return new ResponseEntity<>("The price cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getStock()<=0){
            return new ResponseEntity<>("The stock cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getCategory()==null){
            return new ResponseEntity<>("The category cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getColor()==null){
            return new ResponseEntity<>("The color cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (createProductDTO.getDiscount()<0){
            return new ResponseEntity<>("The discount cannot be 0", HttpStatus.BAD_REQUEST);
        }
        Product product=new Product(createProductDTO);
        productService.save(product);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable long id){
        if (id<=0){
            return new ResponseEntity<>("The id cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (!productService.existsById(id)){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        Product product= productService.findById(id);
        List<Comment> comments=product.getComments();
        for (Comment comment:comments){
            List<Answer> answers=comment.getAnswers();
            for (Answer answer:answers){
                answerService.deleteById(answer.getId());
            }
            commentService.deleteById(comment.getId());
        }
        productService.deleteById(id);
        return new ResponseEntity<>("Success Deleted",HttpStatus.OK);
    }
}
