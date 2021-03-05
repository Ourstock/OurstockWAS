package com.ourstock.api.ourstock_api.controller;


import com.ourstock.api.ourstock_api.dto.order.*;
import com.ourstock.api.ourstock_api.service.OrderService;
import com.ourstock.api.ourstock_api.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


//    @MessageMapping("/order/receive")
//    @SendTo("/transactions")
//    public ResponseEntity<Order> sendToTransaction(
//            @Valid @RequestBody OrderRegisterDto orderRegisterDto,
//            HttpServletRequest httpServletRequest
//    ) {
//        return ResponseEntity.ok(
//                orderService.createOrder(orderRegisterDto,
//                        httpServletRequest.getHeader("JwtAccessToken"))
//        );
//    }


    @PostMapping("/order/register")
    public ResponseEntity<Order> registerBuyOrder(
            @Valid @RequestBody OrderRegisterDto orderRegisterDto
    ) {
        return ResponseEntity.ok(
                orderService.createOrder(orderRegisterDto)
        );
    }

    @GetMapping("/order/detail")
    public ResponseEntity<Order> detailOrder(
            @Valid @RequestBody OrderDetailDto orderDetailDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(orderService.detailOrder(orderDetailDto, httpServletRequest.getHeader("JwtAccessToken")));
    }

    @PostMapping("/order/sell/update")
    public ResponseEntity<Order> updateOrder(
            @Valid @RequestBody OrderUpdateDto orderUpdateDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(orderService.updateOrder(orderUpdateDto, httpServletRequest.getHeader("JwtAccessToken")));
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
