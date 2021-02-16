package com.outstock.api.outstock_api.controller;


import com.outstock.api.outstock_api.dto.order.OrderDeleteDto;
import com.outstock.api.outstock_api.dto.order.OrderRegisterDto;
import com.outstock.api.outstock_api.model.Order;
import com.outstock.api.outstock_api.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    거래 등록
    @PostMapping("/order/register")
    public ResponseEntity<Order> registerOrder (
            @Valid @RequestBody OrderRegisterDto orderRegisterDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                orderService.createOrder(orderRegisterDto,
                        httpServletRequest.getHeader("JwtAccessToken")
                )
        );
    }

//    거래 삭제
    @PostMapping("/order/delete")
    public ResponseEntity<Order> deleteOrder (
            @Valid @RequestBody OrderDeleteDto orderDeleteDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                orderService.deleteOrder(orderDeleteDto,
                        httpServletRequest.getHeader("JwtAccessToken")
                )
        );
    }

}
