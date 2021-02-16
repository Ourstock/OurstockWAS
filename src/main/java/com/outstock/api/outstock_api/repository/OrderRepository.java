package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order deleteByOrderId(int orderId);
}
