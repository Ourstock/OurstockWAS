package com.outstock.api.outstock_api.service;

import com.outstock.api.outstock_api.dto.order.OrderDeleteDto;
import com.outstock.api.outstock_api.dto.order.OrderListDto;
import com.outstock.api.outstock_api.dto.order.OrderRegisterDto;
import com.outstock.api.outstock_api.handler.channel.ChannelNotFoundHandler;
import com.outstock.api.outstock_api.handler.user.JwtTokenHandler;
import com.outstock.api.outstock_api.model.Order;
import com.outstock.api.outstock_api.model.Trade;
import com.outstock.api.outstock_api.repository.OrderRepository;
import com.outstock.api.outstock_api.repository.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TradeRepository tradeRepository;
    private final UserService userService;
    private final ChannelService channelService;
    private final TradeService tradeService;



    public OrderService(
            OrderRepository orderRepository,
            TradeRepository tradeRepository,
            UserService userService,
            ChannelService channelService,
            TradeService tradeService
    ) {
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
        this.userService = userService;
        this.channelService = channelService;
        this.tradeService = tradeService;
    }


//    매도
    @Transactional
    public Order createSellOrder(OrderRegisterDto orderRegisterDto, String jwtToken) {
        if (userService.validJwtToken(orderRegisterDto.getUserId(), jwtToken)) {
            if (channelService.validChannelId(orderRegisterDto.getChannelId())) {
                Order newOrder = Order.builder()
                        .userId(orderRegisterDto.getUserId())
                        .channelId(orderRegisterDto.getChannelId())
                        .orderCount(orderRegisterDto.getOrderCount())
                        .orderCost(orderRegisterDto.getOrderCost())
                        .type(orderRegisterDto.getType())
                        .build();

                Order finalOrder = orderRepository.save(newOrder);
                if (orderRepository.findByOrderCostAndOrderCountAndTypeOrderByRegisterTime(
                        orderRegisterDto.getOrderCost(),
                        orderRegisterDto.getOrderCount(),
                        "buy").isPresent()) {
                    Order transactionBuyOrder = orderRepository.getByOrderCostAndOrderCountAndTypeOrderByRegisterTime(
                            orderRegisterDto.getOrderCost(),
                            orderRegisterDto.getOrderCount(),
                            "buy");
                    long buyOrderId = Long.parseLong(String.valueOf(transactionBuyOrder.getOrderId()));
                    long sellOrderId = Long.parseLong(String.valueOf(newOrder.getOrderId()));
                    int tradeSum = orderRegisterDto.getOrderCount() * orderRegisterDto.getOrderCost();
                    int tradeFee = (int) ((tradeSum / 100) * 0.25);
                    String tradeType = "sell";

                    Trade newTrade = Trade.builder()
                            .buyOrderId((int) buyOrderId)
                            .buyUserId(transactionBuyOrder.getUserId())
                            .sellOrderId((int) sellOrderId)
                            .sellUserId(newOrder.getUserId())
                            .channelId(orderRegisterDto.getChannelId())
                            .tradeCount(orderRegisterDto.getOrderCount())
                            .tradeCost(orderRegisterDto.getOrderCost())
                            .tradeCostSum((int) tradeSum)
                            .fee(tradeFee)
                            .settlementAmount(tradeSum + tradeFee)
                            .lastType(tradeType)
                            .build();
                    tradeRepository.save(newTrade);
                    return finalOrder;
                }
                return finalOrder;
            }
            throw new ChannelNotFoundHandler();
        }
        throw new JwtTokenHandler();
    }


////    매수
//    @Transactional
//    public Order createBuyOrder(OrderRegisterDto orderRegisterDto, String jwtToken) {
//        if (userService.validJwtToken(orderRegisterDto.getUserId(), jwtToken)) {
//            if (channelService.validChannelId(orderRegisterDto.getChannelId())) {
//                Order newOrder = Order.builder()
//                        .userId(orderRegisterDto.getUserId())
//                        .channelId(orderRegisterDto.getChannelId())
//                        .orderCount(orderRegisterDto.getOrderCount())
//                        .orderCost(orderRegisterDto.getOrderCost())
//                        .type(orderRegisterDto.getType())
//                        .build();
////                Order finalOrder = orderRepository.
//            }
//        }
//    }

//    Order 등록을 하고 난뒤에 바로 가능한 거래가 있나 찾은뒤 없으면 save 있으면 채결
//    채결후 trade 등록을 한다.
//    등록된 trade를 return을 어떻게 할것인가...
//

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


}
