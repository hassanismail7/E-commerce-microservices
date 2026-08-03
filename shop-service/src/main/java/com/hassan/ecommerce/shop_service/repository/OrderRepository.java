package com.hassan.ecommerce.shop_service.repository;

import com.hassan.ecommerce.shop_service.entity.Order;
import com.hassan.ecommerce.shop_service.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long orderId, Long userId);

    List<Order> findByUserIdAndStatus(
            Long userId,
            OrderStatus status
    );
}
