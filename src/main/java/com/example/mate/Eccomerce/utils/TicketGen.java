package com.example.mate.Eccomerce.utils;

import com.example.mate.Eccomerce.dtos.DetailsDTO;
import com.example.mate.Eccomerce.dtos.ProductDTO;
import com.example.mate.Eccomerce.dtos.PurchaseOrderDTO;

public class TicketGen {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String cardNumber() {
        return getRandomNumber(1000, 10000) + "-"
                + getRandomNumber(1000, 10000) + "-" +
                getRandomNumber(1000, 10000) + "-" +
                getRandomNumber(1000, 10000);
    }
}