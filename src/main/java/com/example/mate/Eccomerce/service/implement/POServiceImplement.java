package com.example.mate.Eccomerce.service.implement;

import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.repositories.PurchaseOrderRepository;
import com.example.mate.Eccomerce.service.POService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class POServiceImplement implements POService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }
}
