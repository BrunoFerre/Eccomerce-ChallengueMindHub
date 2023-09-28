package com.example.mate.Eccomerce.controllers;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.repositories.PersonRepositories;
import com.example.mate.Eccomerce.service.POService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/purchaseOrder")
public class PurchaseoOrderController {
    @Autowired
    private POService POService;
    @Autowired
    private PersonRepositories personRepositories;

    @GetMapping("/history")
    public List<PurchaseOrder> getHistory(){
        return POService.getAll();
    }

//    @Transactional
//    @PostMapping("/purchaseOrder")
//    public ResponseEntity<Object> createPurchaseOrder(ProductDTO Product){
//        if(Product == null){
//            return new ResponseEntity<>("The product doestn't exist", HttpStatus.BAD_REQUEST);
//        }
//    }
}
