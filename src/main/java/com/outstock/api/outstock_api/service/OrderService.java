package com.outstock.api.outstock_api.service;

import com.outstock.api.outstock_api.dto.order.OrderDeleteDto;
import com.outstock.api.outstock_api.dto.order.OrderRegisterDto;
import com.outstock.api.outstock_api.handler.channel.ChannelNotFoundHandler;
import com.outstock.api.outstock_api.handler.user.JwtTokenHandler;
import com.outstock.api.outstock_api.model.Order;
import com.outstock.api.outstock_api.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ChannelService channelService;


    public OrderService(
            OrderRepository orderRepository,
            UserService userService,
            ChannelService channelService
    ) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.channelService = channelService;
    }

    @Transactional
    public Order createOrder(OrderRegisterDto orderRegisterDto, String jwtToken) {
        if (userService.validJwtToken(orderRegisterDto.getUserId(), jwtToken)) {
            if (channelService.validChannelId(orderRegisterDto.getChannelId())) {
                Order newOrder = Order.builder()
                        .userId(orderRegisterDto.getUserId())
                        .channelId(orderRegisterDto.getChannelId())
                        .orderCount(orderRegisterDto.getOrderCount())
                        .tradeCount(orderRegisterDto.getTradeCount())
                        .restCount(orderRegisterDto.getRestCount())
                        .orderCost(orderRegisterDto.getOrderCost())
                        .type(orderRegisterDto.getType())
                        .status(orderRegisterDto.getStatus())
                        .build();
                return orderRepository.save(newOrder);
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
    }

    @Transactional
    public Order deleteOrder(OrderDeleteDto orderDeleteDto, String jwtToken) {
        if (userService.validJwtToken(orderDeleteDto.getUserId(), jwtToken)) {
            if (channelService.validChannelId(orderDeleteDto.getChannelId())) {
                return orderRepository.deleteByOrderId(orderDeleteDto.getOrderId());
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
    }


}
