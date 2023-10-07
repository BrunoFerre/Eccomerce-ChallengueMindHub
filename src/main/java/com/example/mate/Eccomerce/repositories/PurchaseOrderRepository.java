package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    PurchaseOrder findByTicket(String ticket);
    boolean existsByTicket(String ticket);
}