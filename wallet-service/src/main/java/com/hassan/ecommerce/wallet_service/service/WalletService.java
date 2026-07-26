package com.hassan.ecommerce.wallet_service.service;

import com.hassan.ecommerce.wallet_service.entity.Transaction;
import com.hassan.ecommerce.wallet_service.entity.TransactionType;
import com.hassan.ecommerce.wallet_service.entity.Wallet;
import com.hassan.ecommerce.wallet_service.exception.WalletNotFoundException;
import com.hassan.ecommerce.wallet_service.repository.TransactionRepository;
import com.hassan.ecommerce.wallet_service.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Wallet createWallet(Long userId) {

        Wallet wallet = new Wallet(userId);
        wallet.setBalance(BigDecimal.ZERO);

        return walletRepository.save(wallet);
    }


    public Wallet getWalletById(Long walletId) {

        return walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new WalletNotFoundException("Wallet not found."));
    }


    public Wallet getWalletByUserId(Long userId) {

        return walletRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new WalletNotFoundException("Wallet not found."));
    }

    public Wallet deposit(Long walletId,BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found."));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        wallet.setBalance(wallet.getBalance().add(amount));

        walletRepository.save(wallet);
        transactionRepository.save(new Transaction(wallet, TransactionType.DEPOSIT,amount));

        return wallet;

    }

    public Wallet withdraw(Long walletId, BigDecimal amount) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new WalletNotFoundException("Wallet not found."));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        walletRepository.save(wallet);

        transactionRepository.save(
                new Transaction(wallet, TransactionType.WITHDRAW, amount)
        );

        return wallet;
    }

    public List<Wallet> getAllWallets() {

        return walletRepository.findAll();
    }


    public void deleteWallet(Long walletId) {

        Wallet wallet = getWalletById(walletId);

        walletRepository.delete(wallet);
    }
}
