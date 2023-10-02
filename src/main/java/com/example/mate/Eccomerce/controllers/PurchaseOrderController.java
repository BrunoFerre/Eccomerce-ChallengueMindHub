package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.AdressDTO;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.dtos.PurchaseOrderBuyDTO;
import com.example.mate.Eccomerce.dtos.PurchaseOrderDTO;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.service.POService;
import com.example.mate.Eccomerce.service.PersonService;
import com.example.mate.Eccomerce.utils.TicketGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.mate.Eccomerce.utils.TicketGen.generateTicket;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderController {

    @Autowired
    private POService poService;
    @Autowired
    private PersonService personService;
    @GetMapping("/history")
    public List<PurchaseOrderDTO> getPurchaseHistory(Authentication authentication) {
        Person current = personService.findByEmail(authentication.getName());
        return poService.getPurchaseHistory(current).stream().map(PurchaseOrderDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PurchaseOrderDTO getPurchaseById(@PathVariable long id, Authentication authentication) {
        Person current = personService.findByEmail(authentication.getName());
        return new PurchaseOrderDTO(poService.getPurchaseOrder(id, current));
    }
    @Transactional
    @PostMapping("/purchaseOrder")
    public ResponseEntity<Object> addPurchaseOrder(@RequestBody PurchaseOrderBuyDTO purchaseOrderBuyDTO, Authentication authentication) {
        Person current = personService.findByEmail(authentication.getName());
        LocalDateTime date = purchaseOrderBuyDTO.getOrderDate();
        List<ProductDTO> products = purchaseOrderBuyDTO.getProducts();
        String client = purchaseOrderBuyDTO.getClient();
        String paymentMethod = purchaseOrderBuyDTO.getPaymentMethod();
        double totalAmountToPay = purchaseOrderBuyDTO.getTotalAmountToPay();
        AdressDTO adress = purchaseOrderBuyDTO.getAdress();
        if (current == null) {
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        if (date.isAfter(LocalDateTime.now())) {
            return new ResponseEntity<>("The date is not valid", HttpStatus.BAD_REQUEST);
        }
        if (products == null) {
            return new ResponseEntity<>("your cart cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (paymentMethod.isBlank()) {
            return new ResponseEntity<>("the payment method cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (paymentMethod == null) {
            return new ResponseEntity<>("this payment method doesn't exists", HttpStatus.BAD_REQUEST);
        }
        if (totalAmountToPay <= 0) {
            return new ResponseEntity<>("the total amount to pay cannot be 0 or less than 0", HttpStatus.BAD_REQUEST);
        }
        if (adress == null) {
            return new ResponseEntity<>("please, add an adress", HttpStatus.BAD_REQUEST);
        }
        if(current.getAdress() != adress){
            return new ResponseEntity<>("do you want to change the adress?", HttpStatus.BAD_REQUEST);
        }
        String buyTicket = generateTicket(purchaseOrderBuyDTO);
        PurchaseOrder purchaseOrder = new PurchaseOrder(buyTicket,totalAmountToPay,date);
        poService.save(purchaseOrder);
        current.addPurchaseOrder(purchaseOrder);
        personService.save(current);
        return new ResponseEntity<>("The purchase was successful!", HttpStatus.CREATED);
    }

}
