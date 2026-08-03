package com.hassan.ecommerce.shop_service.service;

import com.hassan.ecommerce.shop_service.dto.ProductResponse;
import com.hassan.ecommerce.shop_service.dto.WithdrawRequest;
import com.hassan.ecommerce.shop_service.entity.*;
import com.hassan.ecommerce.shop_service.exception.CartItemNotFoundException;
import com.hassan.ecommerce.shop_service.exception.CartNotFoundException;
import com.hassan.ecommerce.shop_service.exception.OrderNotFoundException;
import com.hassan.ecommerce.shop_service.feign.InventoryClient;
import com.hassan.ecommerce.shop_service.feign.WalletClient;
import com.hassan.ecommerce.shop_service.repository.CartRepository;
import com.hassan.ecommerce.shop_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private WalletClient walletClient;

    public Order checkout (Long userId){
        BigDecimal totalprice = BigDecimal.ZERO;
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(
                ()-> new CartNotFoundException("Cart not found")
        );

        if(cart.getItems().isEmpty()){
            throw new IllegalArgumentException("Cart is empty.");
        }

        Map<Long, ProductResponse> products = new HashMap<>();

        // Validate stock and calculate total price
        for (CartItem item : cart.getItems()) {

            ProductResponse product = inventoryClient.getProductById(item.getProductId());

            products.put(product.getId(), product);

            boolean available = inventoryClient.isAvailable(
                    product.getId(),
                    item.getQuantity()
            );

            if (!available) {
                throw new IllegalArgumentException("Insufficient stock.");
            }

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            totalprice = totalprice.add(subtotal);
        }

        // Withdraw money
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setUserId(userId);
        withdrawRequest.setAmount(totalprice);

        walletClient.withdraw(withdrawRequest);

        // Create order
        Order order = new Order(userId, totalprice);

        // Create order items
        for (CartItem item : cart.getItems()) {

            ProductResponse product = products.get(item.getProductId());

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            OrderItem orderItem = new OrderItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity(),
                    subtotal
            );

            order.addItem(orderItem);
        }
        order = orderRepository.save(order);

        // decrease the stock
        for (CartItem item : cart.getItems()) {

            inventoryClient.decreaseStock(
                    item.getProductId(),
                    item.getQuantity()
            );
        }

        cart.clearItems();
        cartRepository.save(cart);

        return  order;
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long userId, Long orderId) {
        return orderRepository
                .findByIdAndUserId(orderId, userId)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found."));
    }

    public List<Order> getOrdersByStatus(Long userId, OrderStatus status) {
        return orderRepository.findByUserIdAndStatus(userId, status
        );
    }

}
