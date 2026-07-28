package com.hassan.ecommerce.inventory_service.exception;

public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(String s) {
        super(s);
    }
}
