package com.example.mate.Eccomerce.utils;

import com.example.mate.Eccomerce.dtos.DetailsDTO;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.dtos.PurchaseOrderBuyDTO;

public class TicketGen {
    public static String generateTicket(PurchaseOrderBuyDTO purchaseOrderBuyDTO) {
        StringBuilder ticketBuilder = new StringBuilder();


        ticketBuilder.append("ID de orden: ").append(purchaseOrderBuyDTO.getId()).append("\n");
        ticketBuilder.append("Fecha de orden: ").append(purchaseOrderBuyDTO.getOrderDate()).append("\n");

        ticketBuilder.append("Cliente: ").append(purchaseOrderBuyDTO.getClient()).append("\n");

        ticketBuilder.append("Dirección de envío: ").append(purchaseOrderBuyDTO.getAdress().getFullAddress()).append("\n");

        ticketBuilder.append("Productos comprados:\n");
        for (ProductDTO productDTO : purchaseOrderBuyDTO.getProducts()) {
            ticketBuilder.append("Nombre: ").append(productDTO.getName()).append("\n");
            ticketBuilder.append("Precio unitario: ").append(productDTO.getPrice()).append("\n");
        }

        ticketBuilder.append("Monto total a pagar: ").append(purchaseOrderBuyDTO.getTotalAmountToPay()).append("\n");
        ticketBuilder.append("Método de pago: ").append(purchaseOrderBuyDTO.getPaymentMethod()).append("\n");

        return ticketBuilder.toString();
    }
    }

