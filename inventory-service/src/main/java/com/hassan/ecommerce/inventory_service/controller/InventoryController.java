package com.hassan.ecommerce.inventory_service.controller;

import com.hassan.ecommerce.inventory_service.dto.UpdateQuantityRequest;
import com.hassan.ecommerce.inventory_service.entity.Inventory;
import com.hassan.ecommerce.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getInventoryByProductId(productId)
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Inventory> updateQuantity(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateQuantityRequest request) {

        Inventory inventory = inventoryService.updateQuantity(
                productId,
                request.getQuantity());

        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/{productId}/increase")
    public ResponseEntity<Inventory> increaseStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateQuantityRequest request) {

        Inventory inventory = inventoryService.increaseStock(
                productId,
                request.getQuantity());

        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/{productId}/decrease")
    public ResponseEntity<Inventory> decreaseStock(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateQuantityRequest request) {

        Inventory inventory = inventoryService.decreaseStock(
                productId,
                request.getQuantity());

        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{productId}/availability")
    public ResponseEntity<Boolean> isAvailable(
            @PathVariable Long productId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(
                inventoryService.isAvailable(productId, quantity)
        );
    }
}