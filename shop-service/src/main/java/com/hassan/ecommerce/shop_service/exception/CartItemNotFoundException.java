package com.hassan.ecommerce.shop_service.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String s) {
        super(s);
    }
}
