package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order deleteByOrderId(Long orderId);

    Optional<Order> findByOrderCostAndOrderCountAndTypeOrderByRegisterTime(
            Integer orderCost,
            Integer orderCount,
            String orderType);

    Order getByOrderCostAndOrderCountAndTypeOrderByRegisterTime(
            Integer orderCost,
            Integer orderCount,
            String orderType);

    ArrayList<Order> getAllByChannelId(String channelId);
}
