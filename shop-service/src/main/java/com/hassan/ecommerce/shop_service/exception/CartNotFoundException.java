package com.hassan.ecommerce.shop_service.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String cartNotFound) {
        super(cartNotFound);
    }
}
