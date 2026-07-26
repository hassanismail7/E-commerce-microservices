package com.hassan.ecommerce.wallet_service.exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String s) {
        super(s);
    }
}
