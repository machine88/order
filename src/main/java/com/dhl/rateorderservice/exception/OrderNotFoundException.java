package com.dhl.rateorderservice.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID id){
        super("Order not found: " + id);
    }
}
