package com.ourstock.api.ourstock_api.repository;

import com.ourstock.api.ourstock_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order deleteByOrderId(Long orderId);

    boolean findByUserIdAndOrderId(Long userId, Long orderId);

    Order getByUserIdAndOrderId(Long userId, Long orderId);

//
    @Query(nativeQuery = true, value = "SELECT * FROM order_historys oh WHERE oh.user_id NOT IN (:userId) AND oh.order_cost = :orderCost AND oh.order_count = :orderCount AND oh.rest_count = :orderRestCount AND oh.type = :orderType ORDER BY register_time ASC LIMIT 1")
    Optional<Order> findTransaction(
            @Param("orderCost") long orderCost,
            @Param("orderCount") long orderCount,
            @Param("orderRestCount") long orderRestCount,
            @Param("userId") UUID userId,
            @Param("orderType") String orderType
    );

    @Query(nativeQuery = true, value = "SELECT * FROM order_historys oh WHERE oh.user_id NOT IN (:userId) AND oh.order_cost = :orderCost AND oh.order_count = :orderCount AND oh.rest_count = :orderRestCount AND oh.type = :orderType ORDER BY register_time ASC LIMIT 1")
    Order getTransaction(
            @Param("orderCost") Long orderCost,
            @Param("orderCount") Long orderCount,
            @Param("orderRestCount") Long orderRestCount,
            @Param("userId") UUID userId,
            @Param("orderType") String orderType
    );




    ArrayList<Order> getAllByChannelId(String channelId);
}
