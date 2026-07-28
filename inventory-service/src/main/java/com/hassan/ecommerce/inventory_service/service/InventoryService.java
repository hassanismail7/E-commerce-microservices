package com.hassan.ecommerce.inventory_service.service;

import com.hassan.ecommerce.inventory_service.entity.Inventory;
import com.hassan.ecommerce.inventory_service.exception.InventoryNotFoundException;
import com.hassan.ecommerce.inventory_service.repository.Inventoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private Inventoryrepository inventoryRepository;

    public Inventory getInventoryByProductId(Long productId) {

        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found."));
    }

    public Inventory updateQuantity(Long productId, int quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found."));

        inventory.setQuantity(quantity);

        return inventoryRepository.save(inventory);
    }

    public Inventory increaseStock(Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found."));

        inventory.setQuantity(inventory.getQuantity() + quantity);

        return inventoryRepository.save(inventory);
    }

    public Inventory decreaseStock(Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found."));

        if (inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock.");
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);

        return inventoryRepository.save(inventory);
    }

    public boolean isAvailable(Long productId, int requestedQuantity) {

        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Requested quantity must be greater than zero.");
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found."));

        return inventory.getQuantity() >= requestedQuantity;
    }
}