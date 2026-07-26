package com.hassan.ecommerce.wallet_service.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String s) {
        super(s);
    }
}
