package com.hassan.ecommerce.shop_service.controller;

import com.hassan.ecommerce.shop_service.dto.AddToCartRequest;
import com.hassan.ecommerce.shop_service.dto.UpdateCartItemRequest;
import com.hassan.ecommerce.shop_service.entity.Cart;
import com.hassan.ecommerce.shop_service.security.AuthenticatedUser;
import com.hassan.ecommerce.shop_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<Cart> addToCart(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody AddToCartRequest request) {

        return ResponseEntity.ok(
                cartService.addToCart(user.getUserId(), request)
        );
    }

    // Get the cart of authenticated user
    @GetMapping
    public ResponseEntity<Cart> getCart(
            @AuthenticationPrincipal AuthenticatedUser user) {

        return ResponseEntity.ok(
                cartService.getCart(user.getUserId())
        );
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<Cart> updateQuantity(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.updateQuantity(
                        user.getUserId(),
                        itemId,
                        request.getQuantity()
                )
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long itemId) {

        cartService.removeItem(user.getUserId(), itemId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(
            @AuthenticationPrincipal AuthenticatedUser user) {

        cartService.clearCart(user.getUserId());

        return ResponseEntity.noContent().build();
    }
}

