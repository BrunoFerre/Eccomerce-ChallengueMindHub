package com.example.mate.Eccomerce.repositories;

import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DetailsRepository extends JpaRepository<Details, Long> {
    List<Details> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}