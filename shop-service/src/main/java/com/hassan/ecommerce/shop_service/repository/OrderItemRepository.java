package com.hassan.ecommerce.shop_service.repository;

import com.hassan.ecommerce.shop_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}