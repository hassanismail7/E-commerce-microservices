package com.hassan.ecommerce.shop_service.feign;

import com.hassan.ecommerce.shop_service.dto.WithdrawRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "wallet-service")
public interface WalletClient {

    @PostMapping("/wallet/withdraw")
    void withdraw(
            @RequestBody WithdrawRequest request
    );

}
