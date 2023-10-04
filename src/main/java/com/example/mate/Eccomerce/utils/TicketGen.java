package com.example.mate.Eccomerce.utils;

import com.example.mate.Eccomerce.dtos.DetailsDTO;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.dtos.PurchaseOrderDTO;

public class TicketGen {
    public static String generateTicket(PurchaseOrderDTO purchaseOrderBuyDTO) {
        StringBuilder ticketBuilder = new StringBuilder();


        ticketBuilder.append("ID de orden: ").append(purchaseOrderBuyDTO.getId()).append("\n");
        ticketBuilder.append("Fecha de orden: ").append(purchaseOrderBuyDTO.getDate()).append("\n");

        ticketBuilder.append("Cliente: ").append(purchaseOrderBuyDTO.getPersonID()).append("\n");

        ticketBuilder.append("Dirección de envío: ").append(purchaseOrderBuyDTO.getAdress()).append("\n");

        ticketBuilder.append("Productos comprados:\n");
        for (DetailsDTO detailsDTO : purchaseOrderBuyDTO.getDetails()) {
            ticketBuilder.append("Nombre: ").append(detailsDTO.getProductID()).append("\n");
            ticketBuilder.append("Precio unitario: ").append(detailsDTO).append("\n");
        }

        ticketBuilder.append("Monto total a pagar: ").append(purchaseOrderBuyDTO.getAmount()).append("\n");
        ticketBuilder.append("Método de pago: ").append(purchaseOrderBuyDTO.getPaymentMethod()).append("\n");

        return ticketBuilder.toString();
    }
    }

