package com.ourstock.api.ourstock_api.repository;

import com.ourstock.api.ourstock_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order deleteByOrderId(Long orderId);

    Optional<Order> findFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
            Long orderCost,
            Long orderCount,
            Long orderRestCount,
            String orderType);

    Order getFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
            Long orderCost,
            Long orderCount,
            Long orderRestCount,
            String orderType);



    ArrayList<Order> getAllByChannelId(String channelId);
}
