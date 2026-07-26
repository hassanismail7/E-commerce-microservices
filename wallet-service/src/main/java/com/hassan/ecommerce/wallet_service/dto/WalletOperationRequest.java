package com.hassan.ecommerce.wallet_service.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WalletOperationRequest {
    @NotNull(message = "Amount is required.")
    private BigDecimal amount;


    public WalletOperationRequest() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
