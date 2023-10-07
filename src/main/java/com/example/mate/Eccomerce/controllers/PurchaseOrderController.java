package com.example.mate.Eccomerce.controllers;

import com.example.mate.Eccomerce.dtos.*;
import com.example.mate.Eccomerce.models.*;
import com.example.mate.Eccomerce.repositories.PurchaseOrderRepository;
import com.example.mate.Eccomerce.service.*;
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

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderController {

    @Autowired
    private POService poService;
    @Autowired
    private PersonService personService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

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
    public ResponseEntity<Object> addPurchaseOrder(@RequestBody PurchaseOrderBDTO purchaseOrderBDTO, Authentication authentication) {
        if (authentication==null){
            return new ResponseEntity<>("The user was not found", HttpStatus.NOT_FOUND);
        }
        Person current = personService.findByEmail(authentication.getName());
        if (purchaseOrderBDTO.getAddressId()<=0){
            return new ResponseEntity<>("The address id cannot be 0",HttpStatus.BAD_REQUEST);
        }
        if(current==null){
            return new ResponseEntity<>("The user was not found",HttpStatus.NOT_FOUND);
        }
        Adress adress = addressService.findById(purchaseOrderBDTO.getAddressId());
        if (adress==null){
            return new ResponseEntity<>("The address was not found", HttpStatus.NOT_FOUND);
        }
        if (!current.getAdress().contains(adress)){
            return new ResponseEntity<>("The user already has this address", HttpStatus.BAD_REQUEST);
        }
        if (purchaseOrderBDTO.getDetails().size()<=0){
            return new ResponseEntity<>("The details cannot be empty",HttpStatus.BAD_REQUEST);
        }
        if (purchaseOrderBDTO.getPaymentMethod()==null){
            return new ResponseEntity<>("The payment method cannot be null",HttpStatus.BAD_REQUEST);
        }
        String ticket;
        do {
            ticket = TicketGen.cardNumber();
        }while(purchaseOrderRepository.existsByTicket(ticket));
        PurchaseOrder purchaseOrder = new PurchaseOrder(0, LocalDateTime.now(), purchaseOrderBDTO.getPaymentMethod(), adress, ticket);
        purchaseOrder.setAdress(adress);
        purchaseOrder.setPerson(current);
        double aux=0;
        for (CreateDetailsDTO createDetailsDTO : purchaseOrderBDTO.getDetails()) {
            if (createDetailsDTO.getProductId() <= 0) {
                return new ResponseEntity<>("The product id cannot be 0",HttpStatus.BAD_REQUEST);
            }
            if (createDetailsDTO.getQuantity() <= 0) {
                return new ResponseEntity<>("The quantity cannot be 0",HttpStatus.BAD_REQUEST);
            }
            Product product = productService.findById(createDetailsDTO.getProductId());
            if (product == null) {
                return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
            }
            if (product.getStock() < createDetailsDTO.getQuantity()) {
                return new ResponseEntity<>("The product is out of stock", HttpStatus.BAD_REQUEST);
            }
            Details details = new Details(createDetailsDTO.getQuantity(), product.getPrice());
            detailsService.save(details);
            purchaseOrder.addDetails(details);
            product.addDetails(details);
            product.setStock(product.getStock() - createDetailsDTO.getQuantity());
            productService.save(product);
            aux+=product.getPrice()*createDetailsDTO.getQuantity();
        }
        purchaseOrder.setTicket(ticket);
        purchaseOrder.setAmount(aux);
        current.addPurchaseOrder(purchaseOrder);
        poService.save(purchaseOrder);
        purchaseOrder.setPerson(current);
        personService.save(current);
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(purchaseOrder);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}