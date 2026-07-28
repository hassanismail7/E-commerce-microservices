package com.hassan.ecommerce.inventory_service.repository;

import com.hassan.ecommerce.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepository extends JpaRepository<Product,Long> {
}
