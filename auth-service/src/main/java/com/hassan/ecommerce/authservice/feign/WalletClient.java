package com.hassan.ecommerce.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="wallet-service")
public interface WalletClient {

    @PostMapping("/wallet/{userId}")
    void createWallet(@PathVariable Long userId);

}
