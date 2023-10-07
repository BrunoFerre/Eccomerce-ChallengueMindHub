package com.example.mate.Eccomerce.service;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PurchaseOrder;

import java.util.List;
import java.util.Set;

public interface POService {
    Set<PurchaseOrder> getPurchaseHistory(Person person);

    PurchaseOrder getPurchaseOrder(long id, Person current);
    void save(PurchaseOrder purchaseOrder);

    PurchaseOrder findByTicket(String ticket);
}