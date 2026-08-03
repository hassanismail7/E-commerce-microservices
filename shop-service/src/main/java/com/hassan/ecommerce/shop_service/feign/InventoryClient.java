package com.hassan.ecommerce.shop_service.feign;

import com.hassan.ecommerce.shop_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/products/{id}")
    ProductResponse getProductById(
            @PathVariable Long id
    );

    @GetMapping("/inventory/{productId}/availability")
    Boolean isAvailable(
            @PathVariable Long productId,
            @RequestParam int quantity
    );

    @PutMapping("/inventory/{productId}/decrease")
    void decreaseStock(
            @PathVariable Long productId,
            @RequestParam int quantity
    );
}
