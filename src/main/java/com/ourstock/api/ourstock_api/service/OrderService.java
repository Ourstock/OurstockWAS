package com.ourstock.api.ourstock_api.service;

import com.ourstock.api.ourstock_api.dto.order.OrderDetailDto;
import com.ourstock.api.ourstock_api.dto.order.OrderUpdateDto;
import com.ourstock.api.ourstock_api.handler.order.OrderNotFoundException;
import com.ourstock.api.ourstock_api.repository.TradeRepository;
import com.ourstock.api.ourstock_api.dto.order.OrderDeleteDto;
import com.ourstock.api.ourstock_api.dto.order.OrderRegisterDto;
import com.ourstock.api.ourstock_api.handler.channel.ChannelNotFoundException;
import com.ourstock.api.ourstock_api.handler.user.JwtTokenException;
import com.ourstock.api.ourstock_api.model.Order;
import com.ourstock.api.ourstock_api.model.Trade;
import com.ourstock.api.ourstock_api.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;
    private final UserService userService;
    private final ChannelService channelService;

    public OrderService(
            OrderRepository orderRepository,
            TradeRepository tradeRepository,
            UserService userService,
            ChannelService channelService
    ) {
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
        this.userService = userService;
        this.channelService = channelService;
    }

    @Transactional
    public Order createOrder(OrderRegisterDto orderRegisterDto) {
        if (channelService.validChannelId(orderRegisterDto.getChannelId())) {
            Order newOrder = Order.builder()
                    .userId(orderRegisterDto.getUserId())
                    .channelId(orderRegisterDto.getChannelId())
                    .orderCount(orderRegisterDto.getOrderCount())
                    .restCount(orderRegisterDto.getOrderCount())
                    .orderCost(orderRegisterDto.getOrderCost())
                    .type(orderRegisterDto.getType())
                    .build();

            Order finalOrder = orderRepository.save(newOrder);
            return getOrder(finalOrder);
        }
        throw new ChannelNotFoundException();
    }

    private Order getOrder(Order order) throws IllegalArgumentException {
        if (order.getType().equals("buy")) {
            if (orderRepository.findTransaction(
                    order.getOrderCost(),
                    order.getOrderCount(),
                    order.getOrderCount(),
                    order.getUserId(),
                    "sell").isPresent()) {
                Order transactionSellOrder = orderRepository.getTransaction(
                        order.getOrderCost(),
                        order.getOrderCount(),
                        order.getOrderCount(),
                        order.getUserId(),
                        "sell");

                return getTrade(order, order.getOrderCount(), order.getType(), transactionSellOrder);
            }
        } else if (order.getType().equals("sell")) {
            if (orderRepository.findTransaction(
                    order.getOrderCost(),
                    order.getOrderCount(),
                    order.getOrderCount(),
                    order.getUserId(),
                    "buy").isPresent()) {
                Order transactionBuyOrder = orderRepository.getTransaction(
                        order.getOrderCost(),
                        order.getOrderCount(),
                        order.getOrderCount(),
                        order.getUserId(),
                        "buy");

                return getTrade(order, order.getOrderCount(), order.getType(), transactionBuyOrder);
            }
            return order;
        }
        return order;
    }


    private Order getTrade(Order order, long orderCount, String orderType, Order transactionOrder) {
        Trade newTrade = createTrade(order, transactionOrder, orderType);
        transactionOrder.setRestCount(0);
        transactionOrder.setTradeCount(orderCount);
        order.setRestCount(0);
        order.setTradeCount(orderCount);
        tradeRepository.save(newTrade);
        orderRepository.save(order);
        orderRepository.save(transactionOrder);
        return order;
    }

    private Trade createTrade(Order finalOrder, Order transactionOrder, String orderType) {
        long sellOrderId;
        long buyOrderId;
        if (finalOrder.getType().equals("buy")) {
            sellOrderId = Long.parseLong(String.valueOf(transactionOrder.getOrderId()));
            buyOrderId = Long.parseLong(String.valueOf(finalOrder.getOrderId()));
        } else if (finalOrder.getType().equals("sell")) {
            sellOrderId = Long.parseLong(String.valueOf(finalOrder.getOrderId()));
            buyOrderId = Long.parseLong(String.valueOf(transactionOrder.getOrderId()));
        } else {
            throw new IllegalArgumentException();
        }
        long tradeSum = finalOrder.getOrderCost() * finalOrder.getOrderCount();
        int tradeFee = (int) ((tradeSum / 100) * 0.25);

        return Trade.builder()
                .buyOrderId(buyOrderId)
                .buyUserId(transactionOrder.getUserId())
                .sellOrderId(sellOrderId)
                .sellUserId(finalOrder.getUserId())
                .channelId(finalOrder.getChannelId())
                .tradeCount(finalOrder.getOrderCount())
                .tradeCost(finalOrder.getOrderCost())
                .tradeCostSum(tradeSum)
                .fee(tradeFee)
                .settlementAmount(tradeSum + tradeFee)
                .lastType(orderType)
                .build();
    }

    @Transactional
    public Order updateOrder(OrderUpdateDto orderUpdateDto, String jwtToken) {
        if (channelService.validChannelId(orderUpdateDto.getChannelId())) {
            if (orderRepository.findById(orderUpdateDto.getOrderId()).isPresent()) {
                Order updateOrder = orderRepository.getOne(orderUpdateDto.getOrderId());
                Date nowDateTime = new Date();
                updateOrder.setOrderCount(orderUpdateDto.getOrderCount());
                updateOrder.setOrderCost(orderUpdateDto.getOrderCost());
                updateOrder.setLastChange(nowDateTime);
                Order changedOrder = orderRepository.save(updateOrder);
                return getOrder(changedOrder);
            }
            throw new OrderNotFoundException("Not Found Order");
        }
        throw new ChannelNotFoundException();
    }



    @Transactional
    public Order deleteOrder(OrderDeleteDto orderDeleteDto, String jwtToken) {
        if (channelService.validChannelId(orderDeleteDto.getChannelId())) {
            return orderRepository.deleteByOrderId((long) orderDeleteDto.getOrderId());
        }
        throw new ChannelNotFoundException();
    }


    @Transactional
    public Order detailOrder(OrderDetailDto orderDetailDto, String jwtToken) {
        if (orderRepository.findByUserIdAndOrderId(orderDetailDto.getUserId(), orderDetailDto.getOrderId())) {
            return orderRepository.getByUserIdAndOrderId(orderDetailDto.getUserId(), orderDetailDto.getOrderId());
        }
        throw new OrderNotFoundException();
    }


    @Transactional
    public ArrayList<Order> channelOrders(String channelId) {
        return new ArrayList<Order>(orderRepository.getAllByChannelId(channelId));
    }

//    @Transactional
//    public Trade transactionOrderList(String channelId) {
//        ArrayList<Trade> tradeList = tradeRepository.getAllByChannelId(channelId);
//
//        for (Trade trade : tradeList) {
//
//        }
//    }
}
