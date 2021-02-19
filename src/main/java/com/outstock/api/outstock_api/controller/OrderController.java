package com.outstock.api.outstock_api.controller;


import com.outstock.api.outstock_api.dto.order.OrderDeleteDto;
import com.outstock.api.outstock_api.dto.order.OrderListDto;
import com.outstock.api.outstock_api.dto.order.OrderRegisterDto;
import com.outstock.api.outstock_api.model.Order;
import com.outstock.api.outstock_api.service.OrderService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //    거래 등록
    @PostMapping("/order/sell/register")
    public ResponseEntity<Order> registerSellOrder(
            @Valid @RequestBody OrderRegisterDto orderRegisterDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                orderService.createSellOrder(orderRegisterDto,
                        httpServletRequest.getHeader("JwtAccessToken")
                )
        );
    }

//    @PostMapping("/order/buy/register")
//    public ResponseEntity<Order> registerBuyOrder(
//            @Valid @RequestBody OrderBuyRegister
//    )

    //    거래 삭제
    @PostMapping("/order/delete")
    public ResponseEntity<Order> deleteOrder(
            @Valid @RequestBody OrderDeleteDto orderDeleteDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                orderService.deleteOrder(orderDeleteDto,
                        httpServletRequest.getHeader("JwtAccessToken")
                )
        );
    }

    @GetMapping("/order/list")
    public ArrayList<Order> listOrder(
            @Valid @RequestBody OrderListDto orderListDto) {
        return orderService.channelOrders(orderListDto.getChannelId());
    }



}
