package com.ourstock.api.ourstock_api.service;

import com.ourstock.api.ourstock_api.dto.order.OrderUpdateDto;
import com.ourstock.api.ourstock_api.repository.TradeRepository;
import com.ourstock.api.ourstock_api.dto.order.OrderDeleteDto;
import com.ourstock.api.ourstock_api.dto.order.OrderRegisterDto;
import com.ourstock.api.ourstock_api.handler.channel.ChannelNotFoundHandler;
import com.ourstock.api.ourstock_api.handler.user.JwtTokenHandler;
import com.ourstock.api.ourstock_api.model.Order;
import com.ourstock.api.ourstock_api.model.Trade;
import com.ourstock.api.ourstock_api.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

//    @Transactional
//    public Order updateSellOrder(OrderUpdateDto orderUpdateDto, String jwtToken) {
//        if (userService.validJwtToken(orderUpdateDto.getUserId(), jwtToken)) {
//            if (channelService.validChannelId(orderUpdateDto.getChannelId())) {
//                if (orderRepository.findById(orderUpdateDto.getOrderId()).isPresent()) {
//                    Order updateOrder = orderRepository.getOne(orderUpdateDto.getOrderId());
//                    if (orderRepository.findFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
//                            orderUpdateDto.getOrderCost(),
//                            orderUpdateDto.getOrderCount(),
//                            orderUpdateDto.getOrderCount(),
//                            "buy").isPresent()) {
//
//                    }
//
//
//                }
//
//            }
//        }
//    }

//    매도
    @Transactional
    public Order createSellOrder(OrderRegisterDto orderRegisterDto, String jwtToken) {
        if (userService.validJwtToken(orderRegisterDto.getUserId(), jwtToken)) {
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

                if (orderRepository.findFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
                        orderRegisterDto.getOrderCost(),
                        orderRegisterDto.getOrderCount(),
                        orderRegisterDto.getOrderCount(),
                        "buy").isPresent()) {

                    Order transactionBuyOrder = orderRepository.getFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
                            orderRegisterDto.getOrderCost(),
                            orderRegisterDto.getOrderCount(),
                            orderRegisterDto.getOrderCount(),
                            "buy");

                    Trade newTrade = createOrder(finalOrder, transactionBuyOrder, "sell");

                    transactionBuyOrder.setRestCount(0);
                    finalOrder.setRestCount(0);
                    finalOrder.setTradeCount(orderRegisterDto.getOrderCount());
                    tradeRepository.save(newTrade);
                    orderRepository.save(finalOrder);
                    orderRepository.save(transactionBuyOrder);

                    return finalOrder;
                }
                return finalOrder;
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
    }

    @Transactional
    public Order createBuyOrder(OrderRegisterDto orderRegisterDto, String jwtToken) {
        if (userService.validJwtToken(orderRegisterDto.getUserId(), jwtToken)) {
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

                if (orderRepository.findFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
                        orderRegisterDto.getOrderCost(),
                        orderRegisterDto.getOrderCount(),
                        orderRegisterDto.getOrderCount(),
                        "sell").isPresent()) {
                    Order transactionBuyOrder = orderRepository.getFirstByOrderCostAndOrderCountAndRestCountAndTypeOrderByRegisterTimeAsc(
                            orderRegisterDto.getOrderCost(),
                            orderRegisterDto.getOrderCount(),
                            orderRegisterDto.getOrderCount(),
                            "sell");

                    Trade newTrade = createOrder(finalOrder, transactionBuyOrder, "buy");
                    transactionBuyOrder.setRestCount(0);
                    finalOrder.setRestCount(0);
                    finalOrder.setTradeCount(orderRegisterDto.getOrderCount());
                    tradeRepository.save(newTrade);
                    orderRepository.save(finalOrder);
                    orderRepository.save(transactionBuyOrder);
                    return finalOrder;
                }
                return finalOrder;
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
    }

    private Trade createOrder(Order finalOrder, Order transactionOrder, String orderType) {
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
    public Order deleteOrder(OrderDeleteDto orderDeleteDto, String jwtToken) {
        if (userService.validJwtToken(orderDeleteDto.getUserId(), jwtToken)) {
            if (channelService.validChannelId(orderDeleteDto.getChannelId())) {
                return orderRepository.deleteByOrderId((long) orderDeleteDto.getOrderId());
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
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
