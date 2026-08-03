package com.hassan.ecommerce.wallet_service.controller;

import com.hassan.ecommerce.wallet_service.dto.WalletOperationRequest;
import com.hassan.ecommerce.wallet_service.dto.WithdrawRequest;
import com.hassan.ecommerce.wallet_service.entity.Wallet;
import com.hassan.ecommerce.wallet_service.security.AuthenticatedUser;
import com.hassan.ecommerce.wallet_service.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
   private WalletService walletService;

    @PostMapping("/{userId}")
    public ResponseEntity<Wallet> createWallet(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.createWallet(userId));
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getWalletById(walletId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<Wallet> deposit(
            @PathVariable Long walletId,
            @Valid @RequestBody WalletOperationRequest request) {

        return ResponseEntity.ok(
                walletService.deposit(walletId, request.getAmount())
        );
    }

    // This endpoint is hit or called internally by shop-service
    @PostMapping("/withdraw")
    public ResponseEntity<Wallet> withdraw(
            @Valid @RequestBody WithdrawRequest request) {

        return ResponseEntity.ok(
                walletService.withdraw(
                        request.getUserId(),
                        request.getAmount()
                )
        );
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }

}
