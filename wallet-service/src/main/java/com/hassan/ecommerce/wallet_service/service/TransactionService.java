package com.hassan.ecommerce.wallet_service.service;

import com.hassan.ecommerce.wallet_service.entity.Transaction;
import com.hassan.ecommerce.wallet_service.exception.TransactionNotFoundException;
import com.hassan.ecommerce.wallet_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    public Transaction getTransactionById(Long transactionId) {

        return transactionRepository.findById(transactionId)
                .orElseThrow(() ->
                        new TransactionNotFoundException("Transaction not found."));
    }


    public List<Transaction> getTransactionsByWalletId(Long walletId) {

        return transactionRepository.findByWalletId(walletId);
    }

}
