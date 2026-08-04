package com.hassan.ecommerce.shop_service.feign;

import com.hassan.ecommerce.shop_service.dto.ProductResponse;
import com.hassan.ecommerce.shop_service.dto.UpdateQuantityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "inventory-service",
        configuration = FeignConfiguration.class
)
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
            @RequestBody UpdateQuantityRequest request
    );
}
