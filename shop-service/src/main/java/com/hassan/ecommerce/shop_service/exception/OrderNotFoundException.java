package com.hassan.ecommerce.shop_service.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String s) {
        super(s);
    }
}
