package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.CreatePurchaseOrderDTO;
import com.example.mate.Eccomerce.models.Details;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PurchaseOrderController {


    @PostMapping("/purchaseOrder")
    public ResponseEntity<Object> newPurchaseOrder(@RequestParam CreatePurchaseOrderDTO, Authentication authentication){

}
}
