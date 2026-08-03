package com.hassan.ecommerce.shop_service.controller;

import com.hassan.ecommerce.shop_service.entity.Order;
import com.hassan.ecommerce.shop_service.entity.OrderStatus;
import com.hassan.ecommerce.shop_service.security.AuthenticatedUser;
import com.hassan.ecommerce.shop_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(
            @AuthenticationPrincipal AuthenticatedUser user) {

        return ResponseEntity.ok(
                orderService.checkout(user.getUserId())
        );
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(
            @AuthenticationPrincipal AuthenticatedUser user) {

        return ResponseEntity.ok(
                orderService.getOrders(user.getUserId())
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.getOrderById(
                        user.getUserId(),
                        orderId
                )
        );
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Order>> getOrdersByStatus(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam OrderStatus status) {

        return ResponseEntity.ok(
                orderService.getOrdersByStatus(
                        user.getUserId(),
                        status
                )
        );
    }

}
