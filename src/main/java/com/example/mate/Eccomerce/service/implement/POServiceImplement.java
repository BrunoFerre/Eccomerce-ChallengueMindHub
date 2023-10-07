package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.repositories.PurchaseOrderRepository;
import com.example.mate.Eccomerce.service.POService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class POServiceImplement implements POService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public Set<PurchaseOrder> getPurchaseHistory(Person person) {
        return person.getPurchaseOrder();
    }

    @Override
    public PurchaseOrder getPurchaseOrder(long id, Person current) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder findByTicket(String ticket) {
        return purchaseOrderRepository.findByTicket(ticket);
    }
}