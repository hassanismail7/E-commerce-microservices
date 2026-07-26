package com.hassan.ecommerce.authservice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String s) {
        super(s);
    }
}
