package com.hassan.ecommerce.wallet_service.controller;

import com.hassan.ecommerce.wallet_service.entity.Transaction;
import com.hassan.ecommerce.wallet_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(
            @PathVariable Long transactionId) {

        return ResponseEntity.ok(
                transactionService.getTransactionById(transactionId)
        );
    }

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<Transaction>> getTransactionsByWalletId(
            @PathVariable Long walletId) {

        return ResponseEntity.ok(
                transactionService.getTransactionsByWalletId(walletId)
        );
    }

}
