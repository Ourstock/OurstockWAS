package com.ourstock.api.ourstock_api.controller;


import com.ourstock.api.ourstock_api.service.OrderService;
import com.ourstock.api.ourstock_api.dto.order.OrderDeleteDto;
import com.ourstock.api.ourstock_api.dto.order.OrderListDto;
import com.ourstock.api.ourstock_api.dto.order.OrderRegisterDto;
import com.ourstock.api.ourstock_api.model.Order;
import org.springframework.http.ResponseEntity;
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

//   매도
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

//  매수
    @PostMapping("/order/buy/register")
    public ResponseEntity<Order> registerBuyOrder(
            @Valid @RequestBody OrderRegisterDto orderRegisterDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                orderService.createBuyOrder(orderRegisterDto,
                        httpServletRequest.getHeader("JwtAccessToken"))
        );
    }


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

//    @GetMapping("/order/{channelId}")
//    public ArrayList<Order>
}
